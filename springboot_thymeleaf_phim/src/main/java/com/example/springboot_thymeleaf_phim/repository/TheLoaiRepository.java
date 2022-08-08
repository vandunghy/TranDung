package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheLoaiRepository extends CrudRepository<TheLoai,Integer> {
    @Query("SELECT u FROM TheLoai u WHERE u.tenTheLoai LIKE %:tuKhoa%")
    List<TheLoai> timKiemTheLoai(@Param("tuKhoa") String tuKhoa);
}
