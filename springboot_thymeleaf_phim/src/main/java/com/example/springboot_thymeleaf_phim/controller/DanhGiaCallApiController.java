package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.DanhGia;
import com.example.springboot_thymeleaf_phim.entity.DanhGiaViewModel;
import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import com.example.springboot_thymeleaf_phim.service.DanhGiaDao;
import com.example.springboot_thymeleaf_phim.service.PhimDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class DanhGiaCallApiController {

    @Autowired
    DanhGiaDao danhGiaDao;

    @RequestMapping("/danhgia")
    public String hienThiDanhSach(Model model)
    {
        List<DanhGiaViewModel> lstDanhGia = danhGiaDao.layDanhSachView();
        model.addAttribute("lstDanhGia", lstDanhGia);
        return "QuanLyDanhGiaCallApi";
    }

    @Autowired
    PhimDao phimDao;
    @ModelAttribute("lstPhim")
    public List<Phim> hienThiDanhSachPhim() {
        List<Phim> lstPhim = phimDao.layDanhSach();
        return lstPhim;

    }

}
