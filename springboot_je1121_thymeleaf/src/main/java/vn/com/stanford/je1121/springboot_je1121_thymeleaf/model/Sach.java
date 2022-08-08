package vn.com.stanford.je1121.springboot_je1121_thymeleaf.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SACH")
public class Sach {

    @Id
    @NotNull(message = "Bạn không được bỏ trống mã sách")
    @Length(min = 1, max = 10, message = "Bạn cần nhập mã sách trong khoảng 1-10 kí tự")
    @Column(name = "MA_SACH", nullable = false, length = 10)
    private String maSach;

    @NotBlank(message = "Bạn cần nhập tên sách")
    @Column(name = "TEN_SACH", nullable = false, length = 250)
    private String tenSach;
    @Column(name = "MO_TA", nullable = true, length = 500)
    private String moTa;

    @Column(name = "ANH_SACH", nullable = true, length = 100)
    private String anhSach;

    @NotNull(message = "Bạn cần phải nhập giá sách")
    @Column(name = "GIA_SACH", nullable = true)
    private double giaSach;
    @NotEmpty(message = "Bạn cần phải nhập tên tác giả")
    @Column(name = "MA_TAC_GIA", nullable = true, length = 30)
    private String tacGia;

    @Column(name = "NGAY_TAO", nullable = true)
    private Date ngayTao;
    @Column(name = "NGAY_CAP_NHAT", nullable = true)
    private Date ngayCapNhat;
    @Column(name = "NGAY_DUYET", nullable = true)
    private Date ngayDuyet;
    @Column(name = "DA_DUYET", nullable = true)
    private Integer daDuyet;

    @Column(name = "MA_CHU_DE", nullable = true, length = 10)
    private String maChuDe;

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public Date getNgayDuyet() {
        return ngayDuyet;
    }

    public void setNgayDuyet(Date ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public Integer getDaDuyet() {
        return daDuyet;
    }

    public void setDaDuyet(Integer daDuyet) {
        this.daDuyet = daDuyet;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhSach() {
        return anhSach;
    }

    public void setAnhSach(String anhSach) {
        this.anhSach = anhSach;
    }

    public double getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(double giaSach) {
        this.giaSach = giaSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
