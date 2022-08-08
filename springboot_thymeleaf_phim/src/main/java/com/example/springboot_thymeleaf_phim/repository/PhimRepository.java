package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.Phim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhimRepository extends CrudRepository<Phim,Integer>{

//    @Query("SELECT u FROM Phim u WHERE u.tenPhim LIKE %:tuKhoa%")
////             + " OR u.quocGia LIKE %:quocGia%"
////             + " OR u.theLoai LIKE %:theLoai%"
////             + " OR u.daoDien LIKE %:daoDien%"
////             + " OR u.dienVien LIKE %:maChuDe%")
//    List<Phim> timKiemPhim(@Param("tuKhoa") String tuKhoa);

//            ,@Param("theLoai") Integer theLoai,
//       @Param("quocGia") Integer quocGia,@Param("dienVien") Integer dienVien,@Param("daoDien") Integer daoDien);
//
//    @Query("SELECT p.tenPhim, p.moTa, p.namSanXuat, p.thoiLuong, p.ngayChieu, qg.tenQuocGia,dv.hoTen,dd.hoTen,tl.tenTheLoai," +
//            "p.ngayDuyet,p.ngayChieu,p.trangThai,p.ngayTao,p.ngayCapNhat,nd.hoTen,p.noiDung,p.anh FROM Phim p "
//            +" INNER JOIN QuocGia qg ON p.dienVien = qg.id"
//            +" INNER JOIN DienVien dv ON p.dienVien = dv.id"
//            +" INNER JOIN DaoDien dd ON p.daoDien = dd.id"
//            +" INNER JOIN NguoiDung nd ON p.nguoiDuyet = nd.id"
//            +" INNER JOIN TheLoai tl ON p.theLoai = tl.id")
//    List<Phim>getPhimAll();
}
