package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.service.IHanhDong;

import java.util.List;

public interface DaoDienDao extends IHanhDong<DaoDien> {

    List<DaoDien> timKiemDaoDien(String tuKhoa);
}
