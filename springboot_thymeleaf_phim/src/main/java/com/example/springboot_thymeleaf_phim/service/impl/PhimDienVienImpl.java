package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.PhimDienVien;
import com.example.springboot_thymeleaf_phim.entity.PhimDienVienViewmodel;
import com.example.springboot_thymeleaf_phim.repository.PhimDienVienRepository;
import com.example.springboot_thymeleaf_phim.service.PhimDienVienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phimDienVienDao")
public class PhimDienVienImpl implements PhimDienVienDao {
    @Autowired
    PhimDienVienRepository phimDienVienRepository;

    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public PhimDienVienImpl(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PhimDienVienViewmodel> getAll() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT pdv.Id ,pdv.Vai_Dien,p.ten_phim, dv.ho_ten as tenDienVien \n");
        builder.append("FROM Phim_dien_vien pdv JOIN Phim p On pdv.phim_id = p.id \n");
        builder.append("JOIN dien_vien dv On pdv.dien_vien_id = dv.id \n");
        return jdbcTemplate.query(builder.toString(),new BeanPropertyRowMapper<>(PhimDienVienViewmodel.class));
    }

    @Override
    public List<PhimDienVien> layDanhSach() {
        return (List<PhimDienVien>)phimDienVienRepository.findAll();
    }

    @Override
    public PhimDienVien layChiTietTheoMa(Object id) {
        PhimDienVien objPhimDienVien = null;
        try {
            objPhimDienVien = phimDienVienRepository.findById(Integer.parseInt("" + id)).get();
        } catch (Exception ex) {
            System.out.println("Lỗi không lấy phim diễn viên có id: " + ex.getMessage());
        }
        return objPhimDienVien;
    }

    @Override
    public boolean themMoi(PhimDienVien obj) {
        PhimDienVien objPhimDienVien = phimDienVienRepository.save(obj);
        if (objPhimDienVien != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(PhimDienVien obj) {
        PhimDienVien objPhimDienVien = phimDienVienRepository.findById(obj.getId()).get();
        if (objPhimDienVien != null)
        {
            objPhimDienVien.setHoTen(obj.getHoTen());
            objPhimDienVien.setVaiDien(obj.getVaiDien());
            objPhimDienVien.setPhimId(obj.getPhimId());
            objPhimDienVien.setDienVien(obj.getDienVien());

            phimDienVienRepository.save(objPhimDienVien);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        PhimDienVien objPhimDienVien = layChiTietTheoMa(id);

        if (objPhimDienVien != null) {
            phimDienVienRepository.delete(objPhimDienVien);
            return true;
        }
        return false;
    }



    @Override
    public List<PhimDienVienViewmodel> timKiemPhimDienVien(String tuKhoa,Integer phimId, Integer dienVienId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT pdv.Id ,p.Id, dv.ho_ten as tenDienVien,pdv.vai_dien,p.ten_phim \n");
        builder.append("FROM Phim_dien_vien pdv JOIN Phim p On pdv.phim_id = p.id \n");
        builder.append("JOIN dien_vien dv On pdv.dien_vien_id = dv.id \n");
        builder.append("WHERE (Vai_Dien LIKE '%' || :tuKhoa || '%') \n");
        if (phimId != null) {
            builder.append("AND Phim_ID = :phimId \n");
        }
        if (dienVienId != null) {
            builder.append("AND Dien_Vien_ID = :dienVienId \n");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("phimId", phimId);
        params.addValue("dienVienId", dienVienId);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(PhimDienVienViewmodel.class));
    }

    @Override
    public List<PhimDienVienViewmodel> getDienVienByPhim(Integer phimId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT pdv.Id ,p.Id, dv.ho_ten as tenDienVien \n");
        builder.append("FROM Phim_dien_vien pdv JOIN Phim p On pdv.phim_id = p.id \n");
        builder.append("JOIN dien_vien dv On pdv.dien_vien_id = dv.id \n");
        builder.append("WHERE Phim_ID = :phimId \n");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("phimId", phimId);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(PhimDienVienViewmodel.class));
    }

    @Override
    public List<PhimDienVien> layPhimDienVienTheoPhimId(Integer phimId) {
        return phimDienVienRepository.layPhimDienVienTheoPhimId(phimId);
    }
}
