package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.SachView;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.ChuDeDao;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.SachDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SachController {
    @Autowired
    SachDao sachDao;

    @RequestMapping(value="/admin/quanly-sach")
    public String hienThiDanhSachSach(Model model)
    {
        //Lấy danh sách từ db
        List<Sach> lstSach = sachDao.layDanhSach();

        //Đưa để hiển thị ra view
        model.addAttribute("lstSach", lstSach);
        model.addAttribute("sachSearch", new SachView());

        return "QuanLySach";
    }

    @RequestMapping(value="/admin/timkiem-sach")
    public String timKiemThongTinSach(@ModelAttribute("view") SachView sach, Model model)
    {
        //Lấy danh sách từ db
        List<Sach> lstSach = sachDao.timKiemSach(sach.getTuKhoa(),
                sach.getMaChuDe());

        //Đưa để hiển thị ra view
        model.addAttribute("lstSach", lstSach);
        model.addAttribute("view", sach);

        return "QuanLySach";
    }

    @RequestMapping(value="/admin/sach-add")
    public String hienThiThemMoiSach(Model model)
    {
        model.addAttribute("sach", new Sach());
        return "SachAdd";
    }

    @RequestMapping(value="/admin/themMoiSach", method = RequestMethod.POST)
    public String thucHienThemSach(@ModelAttribute("sach") @Valid Sach objSach, BindingResult result, @RequestParam("fUpload") MultipartFile fUpload, HttpServletRequest request, Model model)
    {
        //Nếu có lỗi xảy ra
        if(result.hasErrors())
        {
            model.addAttribute("message", "Bạn cần phải nhập đầy đủ thông tin");
        }
        else {
            System.out.println("Mã sách: " + objSach.getMaSach());
            System.out.println("Tên sách: " + objSach.getTenSach());
            System.out.println("Mã chủ đề: " + objSach.getMaChuDe());

            boolean isInsert = true;

            //Nếu đã sách đã có thì là TH sửa
            Sach objSachOld = sachDao.layChiTietTheoMa(objSach.getMaSach());
            if (objSachOld != null) {
                isInsert = false;

                objSach.setAnhSach(objSachOld.getAnhSach());
            } 

            //Xử lý upload file trong Spring MVC
            String fileName = "";
            if(fUpload != null)
            {
                //Lấy tên file
                fileName = fUpload.getOriginalFilename();


                //Lấy đường dẫn upload trong file web.xml
                String strPath = "//Users/macbookm1/Documents/Du_phong/springboot_je1121_thymeleaf/src/main/resources/static/images";
                // request.getServletContext().getInitParameter("file-upload");

                try {
                    //Tạo file
                    File file = new File(strPath, fileName);

                    //Ghi ra file
                    fUpload.transferTo(file);

                    //Gán ảnh mới vào thuộc tính
                    objSach.setAnhSach(file.getName());
                }
                catch (IOException ex)
                {
                    System.err.println("Có lỗi xảy ra trong quá trình upload file. Chi tiết: " + ex.getMessage());
                }
            }

            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {
                objSach.setNgayTao(new Date());
                objSach.setNgayCapNhat(new Date());
                objSach.setDaDuyet(0);
                ketQua = sachDao.themMoi(objSach);
            } else {
                objSach.setNgayCapNhat(new Date());
                
                ketQua = sachDao.capNhat(objSach);
            }

            if (ketQua) {
                return "redirect:quanly-sach";
            }
        }

        return "SachAdd";
    }

    /**
     * Hiển thị chi tiết sách
     * @param ma
     * @param model
     * @return
     */
    @RequestMapping(value="/admin/sach-edit/{ma}")
    public String suaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã sách là: " + ma);

        Sach objSach = sachDao.layChiTietTheoMa(ma);

        model.addAttribute("sach", objSach);

        return "SachAdd";
    }

    @RequestMapping(value="/admin/sach-delete/{ma}")
    public String xoaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin nhân viên
            boolean ketQua = sachDao.xoa(ma);

            if(ketQua)
            {
                List<Sach> lstSach = sachDao.layDanhSach();

                model.addAttribute("lstSach", lstSach);
            }
        }

        return "QuanLySach";
    }

    @Autowired
    ChuDeDao chuDeDao;

    /**
     * Hàm hiển thị danh sách chủ đề lên select
     * @return
     */
    @ModelAttribute("lstChuDe")
    public List<ChuDe> hienThiDanhSachChuDe()
    {
        List<ChuDe> lstChuDe = chuDeDao.layDanhSach();

        return lstChuDe;
    }
}
