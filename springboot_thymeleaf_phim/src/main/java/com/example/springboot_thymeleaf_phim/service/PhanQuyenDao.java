package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import com.example.springboot_thymeleaf_phim.service.IHanhDong;

import java.util.List;

public interface PhanQuyenDao extends IHanhDong<PhanQuyen> {
    PhanQuyen layChiTietTheoVaiTro_ChucNang(int vaiTroId, int chucNangId);
    boolean xoaTheoVaiTro_ChucNang(int vaiTroId, int chucNangId);
    List<PhanQuyen> layChiTietTheoVaiTroId(int id);
    List<PhanQuyen> listQuyenChuaPhan(List<Integer> lstChucNangId);
}
