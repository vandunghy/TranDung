package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.NguoiDung;

import java.util.List;

public interface NguoiDungDao extends IHanhDong<NguoiDung>{

    List<NguoiDung> timKiemNguoiDung(String tuKhoa);

    public int laySoLuongNguoiDung();

    public NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap);

    public List<NguoiDung> layDanhSachNguoiDungTheoHoTen(String hoTen);
}
