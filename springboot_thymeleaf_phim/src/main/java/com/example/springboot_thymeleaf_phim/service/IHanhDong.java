package com.example.springboot_thymeleaf_phim.service;

import java.util.List;

public interface IHanhDong <T>{
    List<T> layDanhSach();

    T layChiTietTheoMa(Object id);

    boolean themMoi(T obj);

    boolean capNhat(T obj);

    boolean xoa(Object id);
}
