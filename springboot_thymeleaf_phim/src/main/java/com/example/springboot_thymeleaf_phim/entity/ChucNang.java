package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "CHUC_NANG")
public class ChucNang {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CHUC_NANG")
    @SequenceGenerator(name = "SEQ_CHUC_NANG", sequenceName = "SEQ_CHUC_NANG", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_CHUC_NANG", length = 150)
    private String tenChucNang;

    @Column(name = "MO_TA", length = 200)
    private String moTa;

    @Column(name = "TEN_FROM", length = 200)
    private String tenFrom;

    @Column(name = "MODULE", length = 200)
    private String module;

    @Column(name = "ODER_NUMBER")
    private Long oderNumber;

    public Long getOderNumber() {
        return oderNumber;
    }

    public void setOderNumber(Long oderNumber) {
        this.oderNumber = oderNumber;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTenFrom() {
        return tenFrom;
    }

    public void setTenFrom(String tenFrom) {
        this.tenFrom = tenFrom;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}