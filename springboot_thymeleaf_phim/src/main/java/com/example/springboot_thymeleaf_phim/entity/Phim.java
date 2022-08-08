package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "PHIM")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Phim")
    @SequenceGenerator(name = "SEQ_Phim", sequenceName = "SEQ_Phim", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_PHIM", nullable = false, length = 200)
    private String tenPhim;

    @Column(name = "MO_TA", nullable = true, length = 1000)
    private String moTa;

    @Column(name = "NAM_SAN_XUAT", nullable = true)
    private Date namSanXuat;

    @Column(name = "THOI_LUONG", nullable = true)
    private Integer thoiLuong;

    @Column(name = "NGAY_CHIEU", nullable = true)
    private Date ngayChieu;


    @Column(name = "QUOC_GIA_ID", nullable = true)
    private Integer quocGia;


    @Column(name = "DIEN_VIEN_ID", nullable = true)
    private Integer dienVien;

    @Column(name = "DAO_DIEN_ID", nullable = true)
    private Integer daoDien;


    @Column(name = "THE_LOAI_ID", nullable = true)
    private Integer theLoai;

    @Column(name = "NGAY_DUYET", nullable = true)
    private Date ngayDuyet;

    @Column(name = "TRANG_THAI", nullable = true)
    private int trangThai;

    @Column(name = "NGAY_TAO", nullable = true)
    private Date ngayTao;

    @Column(name = "NGAY_CAP_NHAT", nullable = true)
    private Date ngayCapNhat;

    @Column(name = "ANH", nullable = true)
    private String anh;


    @Column(name = "NGUOI_DUYET_ID", nullable = true)
    private Integer nguoiDuyet;

    @Lob
    @Column(name = "NOI_DUNG", nullable = true)
    private String noiDung;

    @Column(name = "Trailer", nullable = true, length = 500)
    private String traiLer;
}