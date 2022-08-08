package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.DanhGia;
import com.example.springboot_thymeleaf_phim.entity.DanhGiaViewModel;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanhGiaDao extends IHanhDong<DanhGia> {
    List<DanhGiaViewModel> layDanhSachView();
    DanhGiaViewModel layDiemTheoPhimId(Integer id);
    DanhGia getAllByNguoiDungAndPhimId(Integer nguoiDungId, Integer phimId);
}
