package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class PhimCallApiController {

    @Autowired
    PhimDao phimDao;
    @RequestMapping(value = "/phim")
    public String hienThiDanhSachPhim(Model model){

        List<PhimViewModel> lstPhim = phimDao.layDanhSachView();

        model.addAttribute("lstPhim",lstPhim);
        model.addAttribute("phimView",new PhimView());


        return "QuanLyPhimCallApi";
    }

    @RequestMapping(value="/timkiem/phim")
    public String timKiemThongTinPhim(@ModelAttribute("phimView") PhimView phimView, Model model)
    {
        //Lấy danh sách từ db
        List<PhimViewModel> lstPhim = phimDao.timKiemPhim(phimView.getTuKhoa(),phimView.getTheLoai(),
                phimView.getQuocGia(),phimView.getDienVien(),phimView.getDaoDien(),phimView.getStartDate(),phimView.getEndDate());

        //Đưa để hiển thị ra views
        model.addAttribute("lstPhim", lstPhim);
        model.addAttribute("phimView",new PhimView());

        return "QuanLyPhimCallApi";
    }



    @Autowired
    QuocGiaDao quocGiaDao;

    @ModelAttribute("lstQuocGia")
    public List<QuocGia> dienThiDanhSachQuocGia(){
        List<QuocGia> lstQuocGia = quocGiaDao.layDanhSach();

        return lstQuocGia;
    }


    @Autowired
    TheLoaiDao theLoaiDao;
    @ModelAttribute("lstTheLoai")
    public List<TheLoai> hienThiDanhTheLoai() {
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        return lstTheLoai;
    }


    @Autowired
    DienVienDao dienVienDao;
    @ModelAttribute("lstDienVien")
    public List<DienVien> hienThiDanhSachDienVien(){
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        return lstDienVien;
    }

    @Autowired
    DaoDienDao daoDienDao;
    @ModelAttribute("lstDaoDien")
    public List<DaoDien> hienThiDanhSachDaoDien(){
        List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();
        return lstDaoDien;
    }

    @Autowired
    NguoiDungDao nguoiDungDao;
    @ModelAttribute("lstNguoiDung")
    public List<NguoiDung> hienThiDanhSachNguoiDung() {
        List<NguoiDung> lstNguoiDung = nguoiDungDao.layDanhSach();
        return lstNguoiDung;

    }

}
