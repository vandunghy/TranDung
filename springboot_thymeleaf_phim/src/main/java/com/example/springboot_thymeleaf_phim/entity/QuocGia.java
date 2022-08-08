package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "QUOC_GIA")
public class QuocGia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_QUOC_GIA")
    @SequenceGenerator(name = "SEQ_QUOC_GIA", sequenceName = "SEQ_QUOC_GIA", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "MA_QUOC_GIA",nullable = false ,length = 10)
    private String maQuocGia;

    @Column(name = "TEN_QUOC_GIA", nullable = false ,length = 150)
    private String tenQuocGia;

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public String getMaQuocGia() {
        return maQuocGia;
    }

    public void setMaQuocGia(String maQuocGia) {
        this.maQuocGia = maQuocGia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}