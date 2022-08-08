package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class AnhViewModel {

    private Integer id;


    private String tenAnh;


    private String moTa;


    private Integer phim;


    private String ten;


    private String tenPhim;


}
