package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.service.IHanhDong;

import java.util.List;

public interface TheLoaiDao extends IHanhDong<TheLoai> {
    List<TheLoai> timKiemTheLoai(String tuKhoa);
}
