package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.Anh;
import com.example.springboot_thymeleaf_phim.entity.PhimDienVien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhimDienVienRepository extends CrudRepository<PhimDienVien,Integer> {
    @Query("select u from PhimDienVien u where  u.phimId = :phim")
    List<PhimDienVien> layPhimDienVienTheoPhimId(@Param("phim") int phim);
}
