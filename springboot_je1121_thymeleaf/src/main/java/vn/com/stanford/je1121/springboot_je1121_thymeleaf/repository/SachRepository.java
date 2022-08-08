package vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;

import java.util.List;

public interface SachRepository extends CrudRepository<Sach, String> {
    @Query("SELECT u FROM Sach u WHERE u.maSach LIKE %:tuKhoa%"
            + " OR u.tenSach LIKE %:tuKhoa%"
            + " OR u.tacGia LIKE %:tuKhoa%"
            + " OR u.maChuDe LIKE :maChuDe")
    public List<Sach> timKiemSach(@Param("tuKhoa") String tuKhoa, @Param("maChuDe") String maChuDe);
}
