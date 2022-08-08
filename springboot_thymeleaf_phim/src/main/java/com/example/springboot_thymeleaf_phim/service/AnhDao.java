package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.Anh;
import com.example.springboot_thymeleaf_phim.entity.AnhViewModel;

import java.util.List;

public interface AnhDao extends IHanhDong<Anh> {

    public List<Anh> layDanhSachAnhTheoPhimId(Integer phimId);

    public List<Anh> timKiemAnh(String tuKhoa, Integer phimId);

    public List<AnhViewModel> layDanhSachView();

    Anh findByPhimAndTen(Integer phimId, String tenAnh);

    boolean xoaTheoPhimIDVaTen(Integer phimId, String tenAnh);
}
