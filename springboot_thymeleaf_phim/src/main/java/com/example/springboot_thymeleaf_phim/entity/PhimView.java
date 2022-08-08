package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PhimView {

    public String tuKhoa;
    public Integer quocGia;
    public Integer theLoai;
    public Integer dienVien;
    public Integer daoDien;
    private Date startDate;
    private Date endDate;


}
