package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuocGiaReponstory extends CrudRepository<QuocGia,Integer> {
    @Query("SELECT u FROM QuocGia u WHERE u.maQuocGia LIKE %:tuKhoa%"
            + " OR u.tenQuocGia LIKE %:tuKhoa%")
    List<QuocGia> timKiemQuocGia(@Param("tuKhoa") String tuKhoa);
}
