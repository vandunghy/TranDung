package com.example.springboot_thymeleaf_phim.repository.ChiTietPhim;


import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;


import java.util.List;

public interface PhimChiTietDao  {
        List<PhimChiTietView> getAll(Integer phimId);
        List<PhimViewModel> getPhimByTheLoaiOrDienVien(Integer theLoaiId, Integer dienVienId,Integer daoDienId );
}
