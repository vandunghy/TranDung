package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.QuocGiaView;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.entity.TheLoaiView;
import com.example.springboot_thymeleaf_phim.service.TheLoaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class TheLoaiCallApiController {
    @Autowired
    TheLoaiDao theLoaiDao;
    @RequestMapping(value = "/theloai")
    public String hienThiDanhSachTheLoai(Model model) {
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();

        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("view",new TheLoaiView());
        return "TheLoaiCallApi";
    }

    @RequestMapping("/timkiem/theloai")
    public String timKiemThongTinTheLoai(@ModelAttribute("view") TheLoaiView theLoaiView, Model model)
    {
        //Lấy danh sách từ db
        List<TheLoai> lstTheLoai = theLoaiDao.timKiemTheLoai(theLoaiView.getTuKhoa());

        //Đưa để hiển thị ra view
        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("view",new TheLoaiView());

        return "TheLoaiCallApi";
    }
}
