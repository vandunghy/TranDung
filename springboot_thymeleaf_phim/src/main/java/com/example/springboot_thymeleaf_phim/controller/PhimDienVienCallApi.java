package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.service.DienVienDao;
import com.example.springboot_thymeleaf_phim.service.PhimDao;
import com.example.springboot_thymeleaf_phim.service.PhimDienVienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class PhimDienVienCallApi {
    @Autowired
    PhimDienVienDao phimDienVienDao;

    @RequestMapping("/phimdienvien")
    public String hienThiDanhSach(Model model)
    {
        List<PhimDienVienViewmodel> lstPhimDienVien = phimDienVienDao.getAll();

        model.addAttribute("lstPhimDienVien", lstPhimDienVien);
        model.addAttribute("view",new PhimView());
        return "QuanLyPhimDienVienCallApi";
    }

    @RequestMapping(value="/timkiem/phimdienvien")
    public String timKiemThongTinPhim(@ModelAttribute("view") PhimDienVienView phimView, Model model)
    {
        //Lấy danh sách từ db
        List<PhimDienVienViewmodel> lstPhim = phimDienVienDao.timKiemPhimDienVien(phimView.getTuKhoa(),phimView.getPhimId(),phimView.getDienVienID());

        //Đưa để hiển thị ra views
        model.addAttribute("lstPhimDienVien", lstPhim);
        model.addAttribute("view",new PhimView());

        return "QuanLyPhimDienVienCallApi";
    }

    @Autowired
    DienVienDao dienVienDao;
    @ModelAttribute("lstDienVien")
    public List<DienVien> hienThiDanhSachDienVien(){
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        return lstDienVien;
    }

    @Autowired
    PhimDao phimDao;
    @ModelAttribute("lstPhim")
    public List<Phim> hienThiDanhSachPhim()
    {
        List<Phim> lstPhim = phimDao.layDanhSach();
        return lstPhim;
    }
}
