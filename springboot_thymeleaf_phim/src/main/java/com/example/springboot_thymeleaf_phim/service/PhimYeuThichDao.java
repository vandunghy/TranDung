package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import com.example.springboot_thymeleaf_phim.entity.PhimYeuThich;

import java.util.List;

public interface PhimYeuThichDao extends IHanhDong<PhimYeuThich> {
    List<PhimViewModel> getPhimYeuThichByNguoiDung(Integer nguoiDungId);
}
