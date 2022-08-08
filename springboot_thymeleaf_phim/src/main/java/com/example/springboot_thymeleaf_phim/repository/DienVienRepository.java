package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.DienVien;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DienVienRepository extends CrudRepository<DienVien,Integer> {

    @Query("SELECT u FROM DienVien u WHERE u.hoTen LIKE %:tuKhoa%")
    List<DienVien> timKiemDienVien(@Param("tuKhoa") String tuKhoa);
}
