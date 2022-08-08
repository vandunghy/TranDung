package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NHAT_KY")
public class NhatKy {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TEN_NHAT_KY", length = 200)
    private String tenNhatKy;

    @Column(name = "MO_TA", length = 200)
    private String moTa;

    @Column(name = "NGAY_TAO")
    private LocalDate ngayTao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NGUOI_TAO_ID")
    private NguoiDung nguoiTao;

    @Column(name = "TEN_CHUC_NANG", length = 150)
    private String tenChucNang;

    @Column(name = "HANH_DONG", length = 500)
    private String hanhDong;

    @Column(name = "MODULE", length = 200)
    private String module;

    @Column(name = "DIA_CHI_IP", length = 200)
    private String diaChiIp;

    public String getDiaChiIp() {
        return diaChiIp;
    }

    public void setDiaChiIp(String diaChiIp) {
        this.diaChiIp = diaChiIp;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public NguoiDung getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NguoiDung nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenNhatKy() {
        return tenNhatKy;
    }

    public void setTenNhatKy(String tenNhatKy) {
        this.tenNhatKy = tenNhatKy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}