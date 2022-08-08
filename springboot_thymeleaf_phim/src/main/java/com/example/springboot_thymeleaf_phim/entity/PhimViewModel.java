package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;


@Getter
@Setter
public class PhimViewModel {
    private Integer id;

    private Integer phimYTID;

    private String tenPhim;

    private String moTa;

    private String hoTen;

    private Date namSanXuat;

    private Integer thoiLuong;

    private Date ngayChieu;

    private Integer quocGia;

    private String tenQuocGia;

    private String tenDienVien;

    private Integer dienVien;

    private Integer daoDien;

    private String tenDaoDien;

    private Integer theLoai;

    private String tenTheLoai;

    private Date ngayDuyet;

    private String trangThai;

    private Date ngayTao;

    private Date ngayCapNhat;

    private String anh;

    private Integer nguoiDuyet;

    private String noiDung;

    private Integer diem;

}
