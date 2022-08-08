package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.*;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.NguoiDungDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class NguoiDungController {

    @Autowired
    NguoiDungDao nguoiDungDao;

    @RequestMapping(value = "/quanly-nguoidung")
    public String hienThiDanhSachNguoiDung(Model model){

        List<NguoiDung>lstNguoiDung = nguoiDungDao.layDanhSach();

        model.addAttribute("lstNguoiDung",lstNguoiDung);
        model.addAttribute("view",new NguoiDungView());


        return "QuanLyNguoiDung";
    }

    @RequestMapping(value="/timkiem-nguoidung")
    public String timKiemThongNguoiDung(@ModelAttribute("view") NguoiDungView nguoiDungView, Model model)
    {
        //Lấy danh sách từ db
        List<NguoiDung> lstNguoiDung = nguoiDungDao.timKiemNguoiDung(nguoiDungView.getTuKhoa());

        //Đưa để hiển thị ra view
        model.addAttribute("lstNguoiDung", lstNguoiDung);
        model.addAttribute("view",new NguoiDungView());

        return "QuanLyNguoiDung";
    }

    @RequestMapping(value ="nguoidung-add" )
    public String hienThiThemMoiNguoiDung(Model model){

        model.addAttribute("nguoidung",new NguoiDung());

        return "NguoiDungAdd";
    }

    @RequestMapping(value="/themMoiNguoiDung", method = RequestMethod.POST)
    public String thucHienThemSach(@ModelAttribute("nguoidung") @Valid NguoiDung objNguoiDung, BindingResult result, HttpServletRequest request, Model model)
    {
        //Nếu có lỗi xảy ra
        if(result.hasErrors())
        {
            model.addAttribute("message", "Bạn cần phải nhập đầy đủ thông tin");
        }
        else {


            boolean isInsert = true;


            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {

                ketQua = nguoiDungDao.themMoi(objNguoiDung);
            } else {
                ketQua = nguoiDungDao.capNhat(objNguoiDung);
            }

            if (ketQua) {
                return "redirect:quanly-nguoidung";
            }
        }

        return "NguoiDungAdd";
    }

    /**
     * Hiển thị chi tiết sách
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/nguoidung-edit/{ma}")
    public String suaThongTinNguoiDung(@PathVariable("ma")String id, Model model)
    {
        System.out.println("id người dùng: " + id);

        NguoiDung objNguoiDung = nguoiDungDao.layChiTietTheoMa(id);

        model.addAttribute("nguoidung", objNguoiDung);

        return "NguoiDungAdd";
    }

    @RequestMapping(value="/nguoidung-delete/{ma}")
    public String xoaThongTinNguoiDung(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin nhân viên
            boolean ketQua = nguoiDungDao.xoa(ma);

            if(ketQua)
            {
                List<NguoiDung> lstNguoiDung = nguoiDungDao.layDanhSach();

                model.addAttribute("lstNguoiDung", lstNguoiDung);
            }
        }

        return "QuanLyNguoiDung";
    }

}
