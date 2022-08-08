package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.DanhGia;
import com.example.springboot_thymeleaf_phim.entity.PhimYeuThich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhimYeuThichRepository extends JpaRepository<PhimYeuThich,Integer> {
    List<PhimYeuThich> getAllByNguoiDungAndPhimId(int nguoiDungId,int phimId);
}
