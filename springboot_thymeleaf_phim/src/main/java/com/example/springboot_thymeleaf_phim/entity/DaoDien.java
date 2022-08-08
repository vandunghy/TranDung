package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "DAO_DIEN")
public class DaoDien {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DAO_DIEN")
    @SequenceGenerator(name = "SEQ_DAO_DIEN", sequenceName = "SEQ_DAO_DIEN", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Column(name = "Ho_Ten", nullable = false, length = 150)
    private String hoTen;

    @Column(name = "Gioi_Tinh", nullable = true)
    private Integer gioiTinh;



    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO Reverse Engineering! Migrate other columns to the entity
}