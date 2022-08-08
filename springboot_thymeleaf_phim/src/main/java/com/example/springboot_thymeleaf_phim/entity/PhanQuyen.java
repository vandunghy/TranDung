package com.example.springboot_thymeleaf_phim.entity;

import javax.persistence.*;

@Entity
@Table(name = "PHAN_QUYEN")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PHAN_QUYEN")
    @SequenceGenerator(name = "SEQ_PHAN_QUYEN", sequenceName = "SEQ_PHAN_QUYEN", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "CHUC_NANG_ID")
    private int chucNang;


    @Column(name = "VAI_TRO_ID")
    private int vaiTro;

    @Column(name = "XEM")
    private int xem;

    @Column(name = "THEM")
    private int them;

    @Column(name = "SUA")
    private int sua;

    @Column(name = "XOA")
    private int xoa;

    @Column(name = "BAO_CAO", length = 200)
    private String baoCao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getChucNang() {
        return chucNang;
    }

    public void setChucNang(int chucNang) {
        this.chucNang = chucNang;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getXem() {
        return xem;
    }

    public void setXem(int xem) {
        this.xem = xem;
    }

    public int getThem() {
        return them;
    }

    public void setThem(int them) {
        this.them = them;
    }

    public int getSua() {
        return sua;
    }

    public void setSua(int sua) {
        this.sua = sua;
    }

    public int getXoa() {
        return xoa;
    }

    public void setXoa(int xoa) {
        this.xoa = xoa;
    }

    public String getBaoCao() {
        return baoCao;
    }

    public void setBaoCao(String baoCao) {
        this.baoCao = baoCao;
    }
}