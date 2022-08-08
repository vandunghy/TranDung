package vn.com.stanford.je1121.springboot_je1121_thymeleaf.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Phong_Ban")
public class PhongBan {

    @Id
    @Column(name="Ma_Phong" ,nullable=false,length = 10)
    private String maPhong;
    @Column(name = "Ten_Phong",nullable = false,length = 150)
    private String tenPhong;

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }


}
