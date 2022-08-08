package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanhGiaRepository extends CrudRepository<DanhGia,Integer> {
    DanhGia getAllByNguoiDgAndPhim(Integer nguoiDungId, Integer phimId);
}
