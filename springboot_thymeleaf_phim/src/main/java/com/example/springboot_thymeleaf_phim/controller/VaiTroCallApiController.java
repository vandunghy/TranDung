package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.QuocGiaView;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import com.example.springboot_thymeleaf_phim.entity.VaiTroView;
import com.example.springboot_thymeleaf_phim.service.VaiTroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class VaiTroCallApiController {

    @Autowired
    VaiTroDao vaiTroDao;
    @RequestMapping("/vaitro")
    public String hienthiDanhSachVaiTro(Model model){
        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();
        model.addAttribute("lstVaiTro", lstVaiTro);
        model.addAttribute("view",new VaiTroView());
        return "QuanLyVaiTroCallApi";
    }


    @RequestMapping(value="/timkiem/vaitro")
    public String timKiemThongTinVaiTro(@ModelAttribute("view") VaiTroView vaiTroView, Model model)
    {
        //Lấy danh sách từ db
        List<VaiTro> lstVaiTro = vaiTroDao.timKiemVaiTro(vaiTroView.getTuKhoa());

        //Đưa để hiển thị ra view
        model.addAttribute("lstVaiTro", lstVaiTro);
        model.addAttribute("view",new VaiTroView());

        return "QuanLyVaiTroCallApi";
    }
}
