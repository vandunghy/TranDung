package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhanQuyenRepository extends CrudRepository<PhanQuyen,Integer> {
    PhanQuyen findByVaiTroAndChucNang(int vaiTroId, int chucNang);
    List<PhanQuyen> findByChucNangNotIn(List<Integer> lstChucNang);
    List<PhanQuyen> findByVaiTro(int vaiTro);
}
