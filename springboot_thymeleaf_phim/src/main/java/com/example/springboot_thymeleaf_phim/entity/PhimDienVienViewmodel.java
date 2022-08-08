package com.example.springboot_thymeleaf_phim.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhimDienVienViewmodel {

    private int id;
    private String vaiDien;
    private int phimId;
    private String tenPhim;
    private int dienVien;
    private String tenDienVien;

}
