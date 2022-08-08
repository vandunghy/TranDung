package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDeView;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.ChuDeDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ChuDeController {

    @Autowired
    ChuDeDao chuDeDao;

    @RequestMapping(value = "/quanly-chude")
    public String hienThiDanhSachChuDe(Model model){

        List<ChuDe> lstChuDe = chuDeDao.layDanhSach();

        model.addAttribute("lstChuDe",lstChuDe);
        model.addAttribute("view",new ChuDeView());

        return "QuanLyChuDe";
    }

    @RequestMapping(value="/admin/timkiem-chude")
    public String timKiemThongTinChuDe(@ModelAttribute("view") ChuDeView chuDeView, Model model)
    {
        //Lấy danh sách từ db
        List<ChuDe> lstChuDe = chuDeDao.timKiemChuDe(chuDeView.getTuKhoa());

        //Đưa để hiển thị ra view
        model.addAttribute("lstChuDe", lstChuDe);
        model.addAttribute("view",new ChuDeView());

        return "QuanLyChuDe";
    }

    @RequestMapping(value = "/chude-add")
    public String hienThiThemMoiChuDe(Model model){

        model.addAttribute("chude",new ChuDe());

        return "ChuDeAdd";
    }
    @RequestMapping(value="/themMoiChuDe", method = RequestMethod.POST)
    public String thucHienThemMoiCHuDe(@ModelAttribute("chude") @Valid ChuDe objChuDe, BindingResult result,
                                       HttpServletRequest request,Model model)
    {
        if(result.hasErrors()){
            model.addAttribute("messaeges","Bạn phải nhập đầy đủ thông tin");
        }
        else{

            boolean isInsert = true;


            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {
                ketQua = chuDeDao.themMoi(objChuDe);
            } else {
                ketQua = chuDeDao.capNhat(objChuDe);
            }

            if (ketQua) {
                return "redirect:quanly-chude";
            }
        }
        return "ChuDeAdd";
    }
    @RequestMapping(value="/chude-edit/{ma}")
    public String suaThongTinChuDe(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã chủ đề là: " + ma);

        ChuDe objChuDe = (ChuDe) chuDeDao.layChiTietTheoMa(ma);

        model.addAttribute("chude", objChuDe);

        return "ChuDeAdd";
    }

    @RequestMapping(value="/chude-delete/{ma}")
    public String xoaThongTinChuDe(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin nhân viên
            boolean ketQua = chuDeDao.xoa(ma);

            if(ketQua)
            {
                List<ChuDe> lstChuDe = chuDeDao.layDanhSach();

                model.addAttribute("lstChuDe", lstChuDe);
            }
        }

        return "QuanLyChuDe";
    }
}
