package com.example.springboot_thymeleaf_phim.repository.ChiTietPhim;

import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrangChuChiTietController {
    @Autowired
    PhimChiTietDao phimChiTietDao;


    @Autowired
    DanhGiaDao danhGiaDao;

    @Autowired
    TheLoaiDao theLoaiDao;

    @Autowired
    PhimDienVienDao phimDienVienDao;

    @Autowired
    DienVienDao dienVienDao;

    @Autowired
    DaoDienDao daoDienDao;

    @Autowired
    AnhDao anhDao;

    @RequestMapping("chitiet-phim/{id}")
    public String hienThiChiTietPhim(@PathVariable("id") Integer id, Model model) {

        List<PhimChiTietView> lstPhimChiTiet = phimChiTietDao.getAll(id);
        DanhGiaViewModel objDanhGia = danhGiaDao.layDiemTheoPhimId(id);
        List<DanhGiaViewModel> lstDanhGia = new ArrayList<>();
        lstDanhGia.add(objDanhGia);
        List<DanhGiaViewModel> lstDG = danhGiaDao.layDanhSachView();
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        List<PhimDienVien> lstPhimDienVien = phimDienVienDao.layPhimDienVienTheoPhimId(id);
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();
        List<Anh> lstAnh = anhDao.layDanhSachAnhTheoPhimId(id);

        model.addAttribute("lstPhimChiTiet", lstPhimChiTiet);
        model.addAttribute("lstDanhGia", lstDanhGia);
        model.addAttribute("lstDG", lstDG);
        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("lstPhimDienVien", lstPhimDienVien);
        model.addAttribute("lstDienVien",lstDienVien);
        model.addAttribute("lstDaoDien",lstDaoDien);
        model.addAttribute("lstAnh",lstAnh);
        return "TrangChiTietPhim";
    }

    @RequestMapping("/phim-theloai")
    public String hienThiPhimTheoTheLoai(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "dienVienId", required = false) Integer dienVienId,
                                         @RequestParam(value = "daoDienId", required = false) Integer daoDienId ,Model model){

        List<PhimViewModel> lstPhimTheLoai = phimChiTietDao.getPhimByTheLoaiOrDienVien(id, dienVienId,daoDienId);
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        List<DienVien> lstDienVien = dienVienDao.layDanhSach();
        List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();

        model.addAttribute("lstPhimTheLoai",lstPhimTheLoai);
        model.addAttribute("lstTheLoai", lstTheLoai);
        model.addAttribute("lstDienVien",lstDienVien);
        model.addAttribute("lstDaoDien",lstDaoDien);
        return "QuanLyPhimTheoTheLoai";
    }

    @RequestMapping("xemchitiet_phim/{id}")
    public String hienThiChiTietPhimAdmin(@PathVariable("id") Integer id, Model model) {

        List<PhimChiTietView> lstPhimChiTiet = phimChiTietDao.getAll(id);
        List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
        List<PhimDienVien> lstPhimDV = phimDienVienDao.layPhimDienVienTheoPhimId(id);

        model.addAttribute("lstPhimChiTiet", lstPhimChiTiet);
        model.addAttribute("lstTheloai", lstTheLoai);
        model.addAttribute("lstPhimDV", lstPhimDV);
        return "ChiTietPhim";
    }

        @Autowired
        PhimYeuThichDao phimYeuThichDao;

        @RequestMapping("phim-yeuthich")
        public String hienThiPhimYeuThich(@RequestParam(value = "id", required = false) Integer id, Model model, HttpSession session) {

            List<PhimViewModel> lstPhimYeuThich = phimYeuThichDao.getPhimYeuThichByNguoiDung(Integer.parseInt(session.getAttribute("userId")+""));
            List<TheLoai> lstTheLoai = theLoaiDao.layDanhSach();
            List<DienVien> lstDienVien = dienVienDao.layDanhSach();
            List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();

            model.addAttribute("lstPhimYeuThich", lstPhimYeuThich);
            model.addAttribute("lstTheLoai", lstTheLoai);
            model.addAttribute("lstDienVien",lstDienVien);
            model.addAttribute("lstDaoDien",lstDaoDien);
            return "QuanLyPhimYeuThich";
        }

@Autowired
QuocGiaDao quocGiaDao;
    @ModelAttribute("lstQuocGia")
    public List<QuocGia> hienThiDanhSachQuocGia(){
        List<QuocGia> lstQuocGia = quocGiaDao.layDanhSach();

        return lstQuocGia;
    }



}
