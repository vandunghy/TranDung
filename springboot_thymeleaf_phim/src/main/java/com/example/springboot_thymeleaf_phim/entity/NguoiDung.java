package com.example.springboot_thymeleaf_phim.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Setter
@Getter
@Table(name = "NGUOI_DUNG")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Nguoi_Dung")
    @SequenceGenerator(name = "SEQ_Nguoi_Dung", sequenceName = "SEQ_Nguoi_Dung", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "HO_TEN", length = 150,nullable = false)
    private String hoTen;

    @Column(name = "TEN_DANG_NHAP", length = 50,nullable = false)
    private String tenDangNhap;

    @Column(name = "MAT_KHAU", length = 50,nullable = false)
    private String matKhau;

    @Column(name = "DIEN_THOAI", length = 20,nullable = false)
    private String dienThoai;

    @Column(name = "EMAIL", length = 30,nullable = false)
    private String email;

    @Column(name = "DIA_CHI", length = 500,nullable = false)
    private String diaChi;

    @Column(name = "NGAY_TAO",nullable = false)
    private Date ngayTao;


    @Column (name = "VAI_TRO_ID")
    private Integer vaiTro;



}