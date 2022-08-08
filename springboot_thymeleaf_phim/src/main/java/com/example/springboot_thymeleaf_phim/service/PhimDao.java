package com.example.springboot_thymeleaf_phim.service;

import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface PhimDao extends IHanhDong<Phim>{

    public List<PhimViewModel> layDanhSachView();

    public List<PhimViewModel> timKiemPhim(String tuKhoa, Integer theLoai, Integer quocGia, Integer dienVien, Integer daoDien, Date startDate, Date endDate);

    public List<PhimViewModel> layDanhSachPhim();

    Page<Phim> phanTrang(int pageNumber);
}
