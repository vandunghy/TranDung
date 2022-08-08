package vn.com.stanford.je1121.springboot_je1121_thymeleaf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHU_DE")
public class ChuDe {
    @Id
    @Column(name="MA_CHU_DE", nullable = false, length = 10)
    private String maChuDe;


    @Column(name="TEN_CHU_DE", nullable = true, length = 150)
    private String tenChuDe;

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

}
