package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.QuocGia;

import java.util.List;

public interface QuocGiaDao extends IHanhDong<QuocGia> {
    List<QuocGia> timKiemQuocGia(String tuKhoa);
}
