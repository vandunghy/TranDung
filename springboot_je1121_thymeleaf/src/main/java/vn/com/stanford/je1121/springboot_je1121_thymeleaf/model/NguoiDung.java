package vn.com.stanford.je1121.springboot_je1121_thymeleaf.model;


import javax.persistence.*;

@Entity
@Table(name = "Nguoi_Dung")
public class NguoiDung {
    private Integer id;
    private String tenDangNhap;
    private String matKhau;
    private String hoTen;
    private String email;
    private String dienThoai;
    private String diaChi;
    private int roleId;

    @Column(name = "ROLE_ID", unique = true, nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Nguoidung")
    @SequenceGenerator(name = "SEQ_Nguoidung", sequenceName = "SEQ_Nguoidung", allocationSize = 1)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {this.id = id;}

    @Column(name = "Email", unique = true, length = 50)
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Column(name = "Dien_Thoai", unique = true, length = 20)
    public String getDienThoai() {return dienThoai;}

    public void setDienThoai(String dienThoai) {this.dienThoai = dienThoai;}

    @Column(name = "Dia_Chi", unique = true, length = 150)
    public String getDiaChi() {return diaChi;}

    public void setDiaChi(String diaChi) {this.diaChi = diaChi;}


    @Column(name = "Ten_DN", unique = true, length = 50)
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    @Column(name = "Mat_Khau", unique = true, length = 50)
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Column(name = "Ho_Ten", unique = true, length = 30)
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }


}
