package vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.NguoiDung;

import java.util.List;

public interface NguoiDungRepository extends CrudRepository<NguoiDung,Integer> {
    public List<NguoiDung> findByTenDangNhap(String tenDangNhap);

    @Query("select u from NguoiDung u where  u.roleId = :roleId")
    public List<NguoiDung> layNguoiDungTheoRoleId(@Param("roleId") int roleId);

    @Query("select count (*) from NguoiDung u")
    public int laySoLuong();

    public List<NguoiDung> findByHoTenLike(@Param("hoTen") String hoTen);

    @Query("SELECT u FROM NguoiDung u WHERE u.tenDangNhap LIKE :tuKhoa"
            + " OR u.dienThoai LIKE :tuKhoa OR u.hoTen LIKE :tuKhoa")
    public List<NguoiDung> timKiemNguoiDung(@Param("tuKhoa") String tuKhoa);


}

