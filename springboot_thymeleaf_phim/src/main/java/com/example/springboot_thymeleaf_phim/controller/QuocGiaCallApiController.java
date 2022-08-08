package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.QuocGiaView;
import com.example.springboot_thymeleaf_phim.service.QuocGiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class QuocGiaCallApiController {

    @Autowired
    QuocGiaDao quocGiaDao;
    @RequestMapping(value = "/quocgia")
    public String hienThiDanhSachQuocGia(Model model){

        List<QuocGia> lstQuocGia = quocGiaDao.layDanhSach();

        model.addAttribute("lstQuocGia",lstQuocGia);
        model.addAttribute("view",new QuocGiaView());


        return "QuanLyQuocGiaCallApi";
    }

    @RequestMapping(value="/timkiem/quocgia")
    public String timKiemThongTinQuocGia(@ModelAttribute("view") QuocGiaView quocGiaView, Model model)
    {
        //Lấy danh sách từ db
        List<QuocGia> lstQuocGia = quocGiaDao.timKiemQuocGia(quocGiaView.getTuKhoa());

        //Đưa để hiển thị ra view
        model.addAttribute("lstQuocGia", lstQuocGia);
        model.addAttribute("view",new QuocGiaView());

        return "QuanLyQuocGiaCallApi";
    }
}
