package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.NguoiDungViewModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NguoiDungRepository extends CrudRepository<NguoiDung,Integer>{
     List<NguoiDung> findByTenDangNhap(String tenDangNhap);

     @Query("select u from NguoiDung u where  u.vaiTro = :vaiTro")
     List<NguoiDung> layNguoiDungTheoRoleId(@Param("vaiTro") int vaiTro);

     @Query("select count (*) from NguoiDung u")
     int laySoLuong();

     @Query("select u from NguoiDung u where  u.hoTen = :hoTen")
     List<NguoiDung> findByHoTenLike(@Param("hoTen") String hoTen);

//     @Query("SELECT u FROM NguoiDung u WHERE u.tenDangNhap LIKE :tuKhoa"
//            + " OR u.dienThoai LIKE :tuKhoa OR u.hoTen LIKE :tuKhoa  OR u.vaiTro = :vaiTro")
//     List<NguoiDung> timKiemUser(@Param("tuKhoa") String tuKhoa,@Param("vaiTro") int vaiTro);




}
