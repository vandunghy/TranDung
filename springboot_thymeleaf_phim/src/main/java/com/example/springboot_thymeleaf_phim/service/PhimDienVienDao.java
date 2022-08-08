package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.PhimDienVien;
import com.example.springboot_thymeleaf_phim.entity.PhimDienVienViewmodel;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhimDienVienDao extends IHanhDong<PhimDienVien> {
    List<PhimDienVienViewmodel> getAll();
    List<PhimDienVienViewmodel> timKiemPhimDienVien(String tuKhoa,Integer phimId, Integer dienVienId);
    List<PhimDienVienViewmodel> getDienVienByPhim(Integer phimId);
    List<PhimDienVien> layPhimDienVienTheoPhimId(Integer phimId);
}
