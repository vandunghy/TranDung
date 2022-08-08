package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.NguoiDung;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.NguoiDungDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DangNhapController {
    @RequestMapping(value = "/dang-nhap")
    public String hienThiDangNhapHeThong(Model model) {
        //Truyền đối tượng ra bên ngoài view
        model.addAttribute("user", new NguoiDung());
        return "DangNhap";
    }

    @Autowired
    NguoiDungDao nguoiDungDao;

    @PostMapping(value = "/thucHienDangNhap")
    public String thucHienDangNhap(@ModelAttribute("user") NguoiDung user,
                                   Model model,
                                   HttpSession session) {
        System.out.println("Tên đăng nhập: " + user.getTenDangNhap());
        System.out.println("Mật khẩu: " + user.getMatKhau());

        if (user.getTenDangNhap().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên đăng nhập");

            return "DangNhap";
        }
        if (user.getMatKhau().trim().length() == 0) {
            model.addAttribute("thongBao", "Bạn cần phải nhập  mật khẩu");

            return "DangNhap";
        }

        model.addAttribute("user", user);

        NguoiDung objUser = nguoiDungDao.layNguoiDungTheoTenDangNhap(user.getTenDangNhap());

        if (objUser != null) {
            String matKhauDb = objUser.getMatKhau();

            if (user.getMatKhau().equals(matKhauDb)) {
                //Lưu thông tin vào session
                session.setAttribute("userOnline", user.getTenDangNhap());

                return "redirect:/admin/quanly-sach";
            } else {
                model.addAttribute("thongBao", "Tài khoản không chính xác");
            }
        } else {
            model.addAttribute("thongBao", "Tài khoản không tồn tại");
        }

        return "DangNhap";
    }
    @GetMapping(value = "/dang-xuat")
    public String DangXuat(HttpServletRequest request){
        HttpSession session = request.getSession();

        if(session.getAttribute("userOnliene") != null)
        {
            session.invalidate();
        }
        return "redirect:/trang-chu";
    }

}
