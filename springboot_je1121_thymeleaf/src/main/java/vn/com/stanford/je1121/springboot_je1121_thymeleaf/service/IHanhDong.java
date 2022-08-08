package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import java.util.List;

public interface IHanhDong<T>  {
    List<T> layDanhSach();

    T layChiTietTheoMa(Object id);

    boolean themMoi(T obj);

    boolean capNhat(T obj);

    boolean xoa(Object id);
}
