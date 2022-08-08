package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "ANH")
public class Anh {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Anh")
    @SequenceGenerator(name = "SEQ_Anh", sequenceName = "SEQ_Anh", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_ANH", length = 100)
    private String tenAnh;

    @Column(name = "MO_TA", length = 500)
    private String moTa;

   @Column(name = "PHIM_ID")
    private Integer phim;

    @Column(name = "TEN", length = 50)
    private String ten;

    public String getTen() {return ten;}

    public void setTen(String ten) {this.ten = ten;}

    public Integer getPhim() {
        return phim;
    }

    public void setPhim(Integer phim) {
        this.phim = phim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}