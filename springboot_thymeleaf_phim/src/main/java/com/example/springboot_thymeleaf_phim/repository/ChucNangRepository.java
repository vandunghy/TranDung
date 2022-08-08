package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChucNangRepository extends CrudRepository<ChucNang,Integer>{
    @Query("SELECT u FROM ChucNang u WHERE u.tenChucNang LIKE %:tuKhoa%")
    List<ChucNang> timKiemChucNang(@Param("tuKhoa") String tuKhoa);
}
