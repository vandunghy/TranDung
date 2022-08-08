package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.entity.DanhGia;
import com.example.springboot_thymeleaf_phim.entity.PhimView;
import com.example.springboot_thymeleaf_phim.service.ChucNangDao;
import com.example.springboot_thymeleaf_phim.service.DanhGiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class ChucNangCallApiController {


    @Autowired
    ChucNangDao chucNangDao;

    @RequestMapping("/chucnang")
    public String hienThiDanhSach(Model model)
    {
        List<ChucNang> lstChucNang = chucNangDao.layDanhSach();
        model.addAttribute("lstChucNang", lstChucNang);
        model.addAttribute("view",new PhimView() );
        return "QuanLyChucNangCallApi";
    }

    @RequestMapping("/timkiem/chucnang")
    public String timKiemChucNang(@ModelAttribute("view") PhimView view, Model model){
        List<ChucNang> lstChucNang = chucNangDao.timKiemChucNang(view.getTuKhoa());

        model.addAttribute("lstChucNang",lstChucNang);
        model.addAttribute("view",new PhimView() );

        return "QuanLyChucNangCallApi";
    }
}
