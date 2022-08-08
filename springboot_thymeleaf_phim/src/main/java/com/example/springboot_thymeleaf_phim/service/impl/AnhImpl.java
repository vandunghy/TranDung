package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.Anh;
import com.example.springboot_thymeleaf_phim.entity.AnhViewModel;
import com.example.springboot_thymeleaf_phim.entity.NguoiDungViewModel;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import com.example.springboot_thymeleaf_phim.repository.AnhRepository;
import com.example.springboot_thymeleaf_phim.service.AnhDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("anhDao")
public class AnhImpl implements AnhDao {

    @Autowired
    AnhRepository anhRepository;

    @Override
    public List<Anh> layDanhSach() {

        return (List<Anh>) anhRepository.findAll();
    }

    @Override
    public Anh layChiTietTheoMa(Object id) {
        Anh objAnh = null;

        try{
            objAnh = anhRepository.findById(Integer.parseInt(""+id)).get();
        }catch (Exception ex){
            System.out.println("Không thể tì thấy chi tiết ảnh" +ex.getMessage());
            objAnh = null;
        }
        return objAnh;
    }

    @Override
    public boolean themMoi(Anh obj) {
        Anh objAnh = anhRepository.save(obj);

        if(objAnh != null){
            return true;
        }
        return false;

    }

    @Override
    public boolean capNhat(Anh obj) {

        Anh objAnh = anhRepository.findById(obj.getId()).get();

        if(objAnh != null){

            objAnh.setTenAnh(obj.getTenAnh());
            objAnh.setMoTa(obj.getMoTa());
            objAnh.setPhim(obj.getPhim());
            anhRepository.save(objAnh);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {

        Anh objAnh = layChiTietTheoMa(id);
        if(objAnh != null){
             anhRepository.delete(objAnh);
             return true;
        }
        return false;
    }

    @Override
    public List<Anh> layDanhSachAnhTheoPhimId(Integer phimId) {

        return anhRepository.layAnhTheoPhimId(phimId);
    }


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AnhImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Anh> timKiemAnh(String tuKhoa, Integer phimId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT u.id, u.ten_Anh ,u.Mo_Ta,p.ten_Phim FROM Anh u JOIN Phim p ON u.Phim_ID = p.id \n");
        builder.append("WHERE (Ten_Anh LIKE '%' || :tuKhoa || '%') \n");
        if (phimId != null) {
            builder.append("AND Phim_ID = :phimId \n");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("phimId", phimId);

        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(Anh.class));
    }

    @Autowired
    EntityManager entityManager;

    @Override
    public List<AnhViewModel> layDanhSachView() {

        List<AnhViewModel> lstAnh = new ArrayList<>();

        Query query = entityManager
                .createQuery("SELECT a.id,a.tenAnh, a.moTa, p.tenPhim as phim FROM Anh a "
                        +" INNER JOIN Phim p ON a.phim = p.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] r : lstObject) {
            AnhViewModel objAnh = new AnhViewModel();

            objAnh.setId(Integer.parseInt("" + r[0]));
            objAnh.setTenAnh("" + r[1]);
            objAnh.setMoTa("" + r[2]);
            objAnh.setTenPhim("" + r[3]);
            lstAnh.add(objAnh);
        }
        return lstAnh;
    }

    @Override
    public Anh findByPhimAndTen(Integer phimId, String tenAnh) {
        return anhRepository.findByPhimAndTen(phimId,tenAnh);
    }

    @Override
    public boolean xoaTheoPhimIDVaTen(Integer phimId, String tenAnh) {

        Anh objAnh = anhRepository.findByPhimAndTen(phimId,tenAnh);

        if(objAnh != null){
            anhRepository.delete(objAnh);
            return true;
        }
        return false;
    }


}
