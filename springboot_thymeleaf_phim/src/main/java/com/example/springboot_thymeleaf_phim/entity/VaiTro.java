package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "VAI_TRO")
public class VaiTro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VAI_TRO")
    @SequenceGenerator(name = "SEQ_VAI_TRO", sequenceName = "SEQ_VAI_TRO", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_VAI_TRO", length = 200)
    private String tenVaiTro;

    @Column(name = "MO_TA", length = 500)
    private String moTa;

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}