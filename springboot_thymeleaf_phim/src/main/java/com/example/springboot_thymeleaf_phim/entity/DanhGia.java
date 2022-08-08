package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DANH_GIA")
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DANH_GIA")
    @SequenceGenerator(name = "SEQ_DANH_GIA", sequenceName = "SEQ_DANH_GIA", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NGUOI_DG_ID")
    private Integer nguoiDg;

    @Column(name = "DIEM")
    private Integer diem;

    @Column(name = "PHIM_ID")
    private Integer phim;

    public Integer getPhim() {
        return phim;
    }

    public void setPhim(Integer phim) {
        this.phim = phim;
    }


    public Integer getDiem() {
        return diem;
    }

    public void setDiem(Integer diem) {
        this.diem = diem;
    }

    public Integer getNguoiDg() {
        return nguoiDg;
    }

    public void setNguoiDg(Integer nguoiDg) {
        this.nguoiDg = nguoiDg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}