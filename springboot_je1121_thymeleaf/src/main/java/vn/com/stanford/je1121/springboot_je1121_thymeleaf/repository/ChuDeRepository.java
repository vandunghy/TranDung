package vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;

import java.util.List;

public interface ChuDeRepository extends CrudRepository<ChuDe, String> {
    @Query("SELECT u FROM ChuDe u WHERE u.maChuDe LIKE :tuKhoa"
            + " OR u.tenChuDe LIKE :tuKhoa")
    public List<ChuDe> timKiemChuDe(@Param("tuKhoa") String tuKhoa);
    
}
