package com.example.springboot_thymeleaf_phim.repository.ChiTietPhim;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PhimChiTietView {
    private Integer id;
    private String vaiDien;
    private String tenPhim;
    private int thoiLuong;
    private Date ngayChieu;
    private Date namSanXuat;
    private String tenDienVien;
    private String tenDaoDien;
    private String tenTheLoai;
    private String noiDung;
    private String anh;
    private Integer phimId;
    private String traiLer;
    private Integer diem;
    private Integer nguoiDg;


}
