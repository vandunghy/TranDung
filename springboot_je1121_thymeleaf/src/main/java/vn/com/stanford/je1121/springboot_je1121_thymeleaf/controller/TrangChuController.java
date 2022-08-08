package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.ChuDeDao;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.SachDao;

import java.util.List;

@Controller
public class TrangChuController {
    @Autowired
    SachDao sachDao;


    public TrangChuController() {
    }

    @RequestMapping(value = "/trang-chu")
    public String hienThiDanhSach(Model model) {
        List<Sach> lstSach = sachDao.layDanhSach();

        model.addAttribute("lstSach", lstSach);

        return "TrangChuSach";
    }
    @Autowired
    ChuDeDao chuDeDao;

    /**
     * Hàm hiển thị danh sách chủ đề lên select
     * @return
     */
    @ModelAttribute("lstChuDe")
    public List<ChuDe> hienThiDanhSachChuDe()
    {
        List<ChuDe> lstChuDe = chuDeDao.layDanhSach();

        return lstChuDe;
    }
}