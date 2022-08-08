package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.NguoiDungView;
import com.example.springboot_thymeleaf_phim.entity.NguoiDungViewModel;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import com.example.springboot_thymeleaf_phim.service.impl.NguoidungRepositoryView;
import com.example.springboot_thymeleaf_phim.service.NguoiDungDao;
import com.example.springboot_thymeleaf_phim.service.VaiTroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class NguoiDungCallApiController {

    @Autowired
    VaiTroDao vaiTroDao;
    @ModelAttribute("lstVaiTro")
    public List<VaiTro> hienThiDanhSachVaiTro(){

        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();

        return lstVaiTro;
    }

    @Autowired
    NguoiDungDao nguoiDungDao;

    @Autowired
    NguoidungRepositoryView nguoidungRepositoryView;

    @RequestMapping(value = "/users")
    public String hienThiDanhSachNguoiDung(Model model ){

        List<NguoiDungViewModel>lstNguoiDung = nguoidungRepositoryView.layDanhSach();

        model.addAttribute("lstNguoiDung",lstNguoiDung);
        model.addAttribute("User",new NguoiDungView());


        return "QuanLyNguoiDungCallApi";
    }

    @RequestMapping(value="/timkiem/users")
    public String timKiemThongTinNguoiDung(@ModelAttribute("view") NguoiDungView nguoiDungView, Model model)
    {
        //Lấy danh sách từ db
        List<NguoiDungViewModel> lstNguoiDung = nguoidungRepositoryView.timKiemNguoiDung(nguoiDungView.getTuKhoa(),nguoiDungView.getVaiTro());

        //Đưa để hiển thị ra view
        model.addAttribute("lstNguoiDung", lstNguoiDung);
        model.addAttribute("view",new NguoiDungView());

        return "QuanLyNguoiDungCallApi";
    }
}
