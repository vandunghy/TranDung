package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.config.Encryption;
import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import com.example.springboot_thymeleaf_phim.service.NguoiDungDao;
import com.example.springboot_thymeleaf_phim.service.PhanQuyenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DangNhap {
    @RequestMapping(value = "/dang-nhap")
    public String hienThiDangNhap(Model model) {
        //truyền 1 đối tượng ra bên ngoài
        model.addAttribute("user", new NguoiDung());
        return "DangNhap";
    }

    @Autowired
    NguoiDungDao nguoiDungDao;

    @Autowired
    PhanQuyenDao phanQuyenDao;

    @PostMapping(value = "/thucHienDangNhap")
    public String thucHienDangNhap(@ModelAttribute("user") NguoiDung user, Model model, HttpSession session)
    {
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

        PhanQuyen QL_PHIM = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 1);
        int QL_PHIM_XEM = 0;
        int QL_PHIM_THEM = 0;
        int QL_PHIM_SUA = 0;
        int QL_PHIM_XOA = 0;

        if (QL_PHIM != null) {
            QL_PHIM_XEM = QL_PHIM.getXem();
            QL_PHIM_THEM = QL_PHIM.getThem();
            QL_PHIM_SUA = QL_PHIM.getSua();
            QL_PHIM_XOA = QL_PHIM.getXoa();
        }

        PhanQuyen QL_DAODIEN = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 5);
        int QL_DAODIEN_XEM = 0;
        int QL_DAODIEN_THEM = 0;
        int QL_DAODIEN_SUA = 0;
        int QL_DAODIEN_XOA = 0;
        if (QL_DAODIEN != null) {
            QL_DAODIEN_XEM = QL_DAODIEN.getXem();
            QL_DAODIEN_THEM = QL_DAODIEN.getThem();
            QL_DAODIEN_SUA = QL_DAODIEN.getSua();
            QL_DAODIEN_XOA = QL_DAODIEN.getXoa();
        }

        PhanQuyen QL_DIENVIEN = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 4);
        int QL_DIENVIEN_XEM = 0;
        int QL_DIENVIEN_THEM = 0;
        int QL_DIENVIEN_SUA = 0;
        int QL_DIENVIEN_XOA = 0;
        if (QL_DIENVIEN != null) {
            QL_DIENVIEN_XEM = QL_DIENVIEN.getXem();
            QL_DIENVIEN_THEM = QL_DIENVIEN.getThem();
            QL_DIENVIEN_SUA = QL_DIENVIEN.getSua();
            QL_DIENVIEN_XOA = QL_DIENVIEN.getXoa();
        }

        PhanQuyen QL_QUOCGIA = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 3);
        int QL_QUOCGIA_XEM = 0;
        int QL_QUOCGIA_THEM = 0;
        int QL_QUOCGIA_SUA = 0;
        int QL_QUOCGIA_XOA = 0;
        if (QL_QUOCGIA != null) {
            QL_QUOCGIA_XEM = QL_QUOCGIA.getXem();
            QL_QUOCGIA_THEM = QL_QUOCGIA.getThem();
            QL_QUOCGIA_SUA = QL_QUOCGIA.getSua();
            QL_QUOCGIA_XOA = QL_QUOCGIA.getXoa();
        }

        PhanQuyen QL_NGUOIDUNG = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 8);
        int QL_NGUOIDUNG_XEM = 0;
        int QL_NGUOIDUNG_THEM = 0;
        int QL_NGUOIDUNG_SUA = 0;
        int QL_NGUOIDUNG_XOA = 0;
        if (QL_NGUOIDUNG != null) {
            QL_NGUOIDUNG_XEM = QL_NGUOIDUNG.getXem();
            QL_NGUOIDUNG_THEM = QL_NGUOIDUNG.getThem();
            QL_NGUOIDUNG_SUA = QL_NGUOIDUNG.getSua();
            QL_NGUOIDUNG_XOA = QL_NGUOIDUNG.getXoa();
        }

        PhanQuyen QL_VAITRO = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 7);
        int QL_VAITRO_XEM = 0;
        int QL_VAITRO_THEM = 0;
        int QL_VAITRO_SUA = 0;
        int QL_VAITRO_XOA = 0;
        if (QL_VAITRO != null) {
            QL_VAITRO_XEM = QL_VAITRO.getXem();
            QL_VAITRO_THEM = QL_VAITRO.getThem();
            QL_VAITRO_SUA = QL_VAITRO.getSua();
            QL_VAITRO_XOA = QL_VAITRO.getXoa();
        }


        PhanQuyen QL_PHANQUYEN = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(objUser.getVaiTro(), 9);
        int QL_PHANQUYEN_XEM = 0;
        int QL_PHANQUYEN_THEM = 0;
        int QL_PHANQUYEN_SUA = 0;
        int QL_PHANQUYEN_XOA = 0;
        if (QL_PHANQUYEN != null) {
            QL_PHANQUYEN_XEM = QL_PHANQUYEN.getXem();
            QL_PHANQUYEN_THEM = QL_PHANQUYEN.getThem();
            QL_PHANQUYEN_SUA = QL_PHANQUYEN.getSua();
            QL_PHANQUYEN_XOA = QL_PHANQUYEN.getXoa();
        }
        if (objUser != null) {
            String matKhauDb = objUser.getMatKhau();

            if (Encryption.MD5(user.getMatKhau()).equals(matKhauDb)) {
                //Lưu thông tin vào session
                session.setAttribute("userOnline", user.getTenDangNhap());
                session.setAttribute("userId",objUser.getId());
                session.setAttribute("QL_PHIM_XEM", QL_PHIM_XEM);

                session.setAttribute("QL_DAODIEN_XEM", QL_DAODIEN_XEM);

                session.setAttribute("QL_DAODIEN_XEM", QL_DAODIEN_XEM);

                session.setAttribute("QL_QUOCGIA_XEM", QL_QUOCGIA_XEM);

                session.setAttribute("QL_NGUOIDUNG_XEM", QL_NGUOIDUNG_XEM);

                session.setAttribute("QL_VAITRO_XEM", QL_VAITRO_XEM);

                session.setAttribute("QL_PHANQUYEN_XEM", QL_PHANQUYEN_XEM);

                if(objUser.getVaiTro() ==3 ||objUser.getVaiTro() ==4 ||objUser.getVaiTro() ==4 ||objUser.getVaiTro() ==6 ){
                    return "redirect:/call-api/phim";
                }else {
                    return "redirect:/trang-chu";
                }

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

    @RequestMapping(value = "/dang-ky")
    public String hienThiDangKy(Model model) {
        //truyền 1 đối tượng ra bên ngoài
        model.addAttribute("user", new NguoiDung());
        return "DangKy";
    }

    @PostMapping(value = "/thucHienDangKy")
    public String thucHienDangKy(@ModelAttribute("user") NguoiDung user, Model model){
        if(user.getHoTen().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập họ tên");
            return "DangKy";
        }
        if(user.getTenDangNhap().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập tên đăng nhập");
            return "DangKy";
        }
        if(user.getMatKhau().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập mật khẩu");
            return "DangKy";
        }
        if(user.getDienThoai().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập điện thoại");
            return "DangKy";
        }
        if(user.getEmail().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập email");
            return "DangKy";
        }if(user.getDiaChi().trim().length() == 0){
            model.addAttribute("thongBao","Bạn cần phải nhập địa chỉ");
            return "DangKy";
        }

        NguoiDung oldUser = nguoiDungDao.layNguoiDungTheoTenDangNhap(user.getTenDangNhap());
        if (oldUser != null) {
            if(user.getEmail().equals(oldUser.getEmail())){
                model.addAttribute("thongBao","Địa chỉ email đã được sử dụng");
                return  "DangKy";
            }
            model.addAttribute("thongBao","Tên Đăng nhập Đã được sử dụng");
            return  "DangKy";

        }else {
            String password = user.getMatKhau();
            user.setMatKhau(Encryption.MD5(password));
            user.setVaiTro(1);
            boolean ketQua =  nguoiDungDao.themMoi(user);
            if(ketQua){
                return "redirect:/dang-nhap";
            }
        }
        return "DangKy";
        }
    }


