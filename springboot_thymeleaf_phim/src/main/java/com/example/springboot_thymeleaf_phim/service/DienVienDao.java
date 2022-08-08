package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.DienVien;

import java.util.List;

public interface DienVienDao extends IHanhDong<DienVien> {

    List<DienVien> timKiemDienVien(String tuKhoa);
}
