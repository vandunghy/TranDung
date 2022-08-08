package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import com.example.springboot_thymeleaf_phim.service.ChucNangDao;
import com.example.springboot_thymeleaf_phim.service.PhanQuyenDao;
import com.example.springboot_thymeleaf_phim.service.VaiTroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class PhanQuyenCallApiController {
    @Autowired
    PhanQuyenDao phanQuyenDao;

    @RequestMapping("phanquyen")
    public String hienThiPhanQuyen(Model model) {
        model.addAttribute("lstQuyenTheoVaiTro", new PhanQuyen());
        model.addAttribute("lstQuyenChuaPhan", new PhanQuyen());
        return "QuanLyPhanQuyenCallApi";
    }

    @GetMapping("/phanquyen/{id}")
    public String layChiTietQuyenCuaVaiTro(@PathVariable("id") int id, Model model) {
        List<PhanQuyen> lstQuyenTheoVaiTro = phanQuyenDao.layChiTietTheoVaiTroId(id);
        List<Integer> lstQuyenDaPhan = new ArrayList<>();
        for (int i = 0; i < lstQuyenTheoVaiTro.size(); i++) {
            lstQuyenDaPhan.add(lstQuyenTheoVaiTro.get(i).getChucNang());
        }

        List<PhanQuyen> lstQuyenChuaPhan = new ArrayList<>();

        if (lstQuyenDaPhan.size() == 0) {
            lstQuyenChuaPhan = phanQuyenDao.layChiTietTheoVaiTroId(1);
        } else {
            lstQuyenChuaPhan = phanQuyenDao.listQuyenChuaPhan(lstQuyenDaPhan);
        }


        model.addAttribute("lstQuyenTheoVaiTro", lstQuyenTheoVaiTro);
        model.addAttribute("lstQuyenChuaPhan", lstQuyenChuaPhan);

        return "QuanLyPhanQuyenCallApi";
    }


    @Autowired
    VaiTroDao vaiTroDao;

    @ModelAttribute("lstVaiTro")
    public List<VaiTro> danhSachVaiTro() {
        return vaiTroDao.layDanhSach();
    }

    @Autowired
    ChucNangDao chucNangDao;

    @ModelAttribute("lstChucNang")
    public List<ChucNang> danhSachChucNang() {
        return chucNangDao.layDanhSach();
    }

}
