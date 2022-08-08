package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;

import java.util.List;

public interface VaiTroDao extends IHanhDong<VaiTro>{
    List<VaiTro> timKiemVaiTro(String tuKhoa);
}
