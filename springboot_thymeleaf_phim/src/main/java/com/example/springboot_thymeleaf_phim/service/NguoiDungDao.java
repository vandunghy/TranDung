package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;

import java.util.List;

public interface NguoiDungDao extends IHanhDong<NguoiDung> {

    public int laySoLuongNguoiDung();

    public NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap);

    public List<NguoiDung> layDanhSachNguoiDungTheoHoTen(String hoTen);

}
