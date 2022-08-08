package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.DienVien;
import com.example.springboot_thymeleaf_phim.entity.PhimView;
import com.example.springboot_thymeleaf_phim.service.DienVienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class DienVienCalApiController {
    @Autowired
    DienVienDao dienVienDao;

    @RequestMapping("/dienvien")
    public String hienThiDanhSachDienvien(Model model)
    {
        List<DienVien> lstDienVien= dienVienDao.layDanhSach();
        model.addAttribute("lstDienVien", lstDienVien);
        return "QuanLyDienVienCallApi";
    }
    @RequestMapping("/timkiem/dienvien")
    public String timKiemDaoDien(@ModelAttribute("view") PhimView view, Model model){
        List<DienVien> lstDienVien = dienVienDao.timKiemDienVien(view.getTuKhoa());

        model.addAttribute("lstDienVien",lstDienVien);
        model.addAttribute("view",new PhimView() );

        return "QuanLyDienVienCallApi";
    }
}
