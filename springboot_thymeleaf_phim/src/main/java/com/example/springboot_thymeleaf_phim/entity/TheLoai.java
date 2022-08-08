package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "THE_LOAI")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_THE_LOAI")
    @SequenceGenerator(name = "SEQ_THE_LOAI", sequenceName = "SEQ_THE_LOAI", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_THE_LOAI", length = 150)
    private String tenTheLoai;

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}