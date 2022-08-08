package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.service.IHanhDong;

import java.util.List;

public interface ChucNangDao extends IHanhDong<ChucNang> {
    List<ChucNang> timKiemChucNang(String tuKhoa);
}
