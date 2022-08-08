package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trang-chu")
public class TrangChu {

    @Autowired
    PhimDao phimDao;

    @Autowired
    TheLoaiDao theLoaiDao;

    @Autowired
    DienVienDao dienVienDao;

    @Autowired
    QuocGiaDao quocGiaDao;

    @Autowired
    DaoDienDao daoDienDao;

    @RequestMapping(value = "")
    public String trangChuPhim( Model model) {
        //Lấy danh sách từ db
        List<PhimViewModel> lstPhim = phimDao.layDanhSachPhim();
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        List<DaoDien> lstDaoDien= daoDienDao.layDanhSach();

        //Đưa để hiển thị ra views
        model.addAttribute("lstPhim", lstPhim);
        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("lstDienVien", lstDienVien);
        model.addAttribute("lstDaoDien", lstDaoDien);
        model.addAttribute("phimView",new PhimView());

        return "TrangChuPhim";
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

        return "DanhSachPhim";
    }

    @RequestMapping(value = "/phim-danhsach")
    public String danhSachPhim(Model model) {

        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        List<DaoDien> lstDaoDien= daoDienDao.layDanhSach();

        Page<Phim> page = phimDao.phanTrang(1);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Phim> lstPhim = page.getContent();

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("lstPhim", lstPhim);
        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("lstDienVien", lstDienVien);
        model.addAttribute("lstDaoDien", lstDaoDien);
        return "DanhSachPhim";
    }

    @GetMapping(value = "/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber")int currentPage) {

        Page<Phim> page = phimDao.phanTrang(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Phim> lstPhim = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("lstPhim", lstPhim);

        return "DanhSachPhim";
    }


    @ModelAttribute("lstQuocGia")
    public List<QuocGia> dienThiDanhSachQuocGia(){
        List<QuocGia> lstQuocGia = quocGiaDao.layDanhSach();

        return lstQuocGia;
    }
}
