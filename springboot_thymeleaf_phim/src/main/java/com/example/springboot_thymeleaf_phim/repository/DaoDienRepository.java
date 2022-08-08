package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DaoDienRepository extends CrudRepository<DaoDien,Integer> {

    @Query("SELECT u FROM DaoDien u WHERE u.hoTen LIKE %:tuKhoa%")
    List<DaoDien> timKiemDaoDien(@Param("tuKhoa") String tuKhoa);
}
