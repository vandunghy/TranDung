package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.PhimView;
import com.example.springboot_thymeleaf_phim.service.DaoDienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class DaoDienCallApiController {
    @Autowired
    DaoDienDao daoDienDao;

    @RequestMapping("/daodien")
    public String hienThiDanhSach(Model model)
    {
        List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();
        model.addAttribute("lstDaoDien", lstDaoDien);
        return "QuanLyDaoDienCallApi";
    }

    @RequestMapping("/timkiem/daodien")
    public String timKiemDaoDien(@ModelAttribute ("view") PhimView view,Model model){
         List<DaoDien> lstDaoDien = daoDienDao.timKiemDaoDien(view.getTuKhoa());

         model.addAttribute("lstDaoDien",lstDaoDien);
         model.addAttribute("view",new PhimView() );

         return "QuanLyDaoDienCallApi";
    }
}
