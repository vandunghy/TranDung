package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class DanhGiaViewModel {
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NGUOI_DG_ID")
    private Integer nguoiDg;

    @Column(name = "DIEM")
    private Integer diem;

    @Column(name = "PHIM_ID")
    private Integer phim;

    @Column(name = "Ho_Ten", nullable = true)
    private String hoTen;

    @Column(name = "Ten_Phim", nullable = true)
    private String tenPhim;

}
