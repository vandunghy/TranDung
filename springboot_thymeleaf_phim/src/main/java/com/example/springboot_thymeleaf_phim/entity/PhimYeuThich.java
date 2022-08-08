package com.example.springboot_thymeleaf_phim.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PHIM_YEU_THICH")
public class PhimYeuThich {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PHIM_YEU_THICH")
    @SequenceGenerator(name = "SEQ_PHIM_YEU_THICH", sequenceName = "SEQ_PHIM_YEU_THICH", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "PHIM_ID")
    private Integer phimId;

    @Column(name = "NGUOI_DUNG_ID")
    private Integer nguoiDung;


}