//package com.example.springboot_thymeleaf_phim.repository.impl;
//
//import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class NguoiDungCustomRepositoryImpl implements NguoiDungCustomRepository {
//
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public NguoiDungCustomRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<NguoiDung> timKiemNguoiDung(String tuKhoa, Integer vaiTro) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT * FROM NGUOI_DUNG \n");
//        builder.append("WHERE (HO_TEN LIKE '%' || :tuKhoa || '%' \n");
//        builder.append("OR TEN_DANG_NHAP LIKE '%' || :tuKhoa || '%' \n ");
//        builder.append("OR DIEN_THOAI LIKE '%' || :tuKhoa || '%') \n");
//        if (vaiTro != null) {
//            builder.append("AND VAI_TRO_ID = :vaiTro \n");
//        }
//
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("tuKhoa", tuKhoa);
//        params.addValue("vaiTro", vaiTro);
//
//        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(NguoiDung.class));
//    }
//
//    @Override
//    public NguoiDung timNguoiDung(String tuKhoa, Integer vaiTro) {
//        StringBuilder builder = new StringBuilder();
//        StringBuilder append = builder.append("SELECT * FROM NGUOI_DUNG \n");
//        builder.append("WHERE (HO_TEN LIKE '%' || :tuKhoa || '%' \n");
//        builder.append("OR TEN_DANG_NHAP LIKE '%' || :tuKhoa || '%' \n ");
//        builder.append("OR DIEN_THOAI LIKE '%' || :tuKhoa || '%') \n");
//        if (vaiTro != null) {
//            builder.append("AND VAI_TRO_ID = :vaiTro \n");
//        }
//
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("tuKhoa", tuKhoa);
//        params.addValue("vaiTro", vaiTro);
//
//        List<NguoiDung> result = jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(NguoiDung.class));
//        if (result != null) {
//            return result.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public List<NguoiDung> getUsersAll() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("SELECT nd.TEN_DANG_NHAP,nd.HO_TEN,nd.DIEN_THOAI,\n");
//        builder.append("nd.EMAIL,nd.DIA_CHI,nd.NGAY_TAO,vt.TEN_VAI_TRO\n");
//        builder.append("FROM NGUOI_DUNG nd JOIN VAI_TRO vt ON nd.VAI_TRO_ID = vt.ID \n ");
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        return jdbcTemplate.query(builder.toString(),new BeanPropertyRowMapper<>(NguoiDung.class));
//    }
//}
