package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "PHIM_DIEN_VIEN")
public class PhimDienVien {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Phim_DIEN_VIEN")
    @SequenceGenerator(name = "SEQ_Phim_DIEN_VIEN", sequenceName = "SEQ_Phim_DIEN_VIEN", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "HO_TEN", length = 150)
    private String hoTen;

    @Column(name = "VAI_DIEN", length = 150)
    private String vaiDien;

    @Column(name = "PHIM_ID")
    private Integer phimId;


    @Column(name = "DIEN_VIEN_ID")
    private Integer dienVien;

    public Integer getDienVien() {
        return dienVien;
    }

    public void setDienVien(Integer dienVien) {
        this.dienVien = dienVien;
    }

    public Integer getPhimId() {
        return phimId;
    }

    public void setPhimId(Integer phimId) {
        this.phimId = phimId;
    }

    public String getVaiDien() {
        return vaiDien;
    }

    public void setVaiDien(String vaiDien) {
        this.vaiDien = vaiDien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}