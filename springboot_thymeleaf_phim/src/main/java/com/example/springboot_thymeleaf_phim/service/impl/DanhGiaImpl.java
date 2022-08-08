package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.repository.DanhGiaRepository;
import com.example.springboot_thymeleaf_phim.service.DanhGiaDao;
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

@Service("danhGiaDao")
public class DanhGiaImpl implements DanhGiaDao {

    @Autowired
    DanhGiaRepository danhGiaRepository;

    @Override
    public List<DanhGia> layDanhSach() {
        return (List<DanhGia>) danhGiaRepository.findAll();
    }

    @Override
    public DanhGia layChiTietTheoMa(Object id) {
        DanhGia objDanhGia = null;
        try {
            objDanhGia = danhGiaRepository.findById(Integer.parseInt("" + id)).get();
        }
        catch(Exception ex)
        {
            System.out.println("Không lấy được người dùng. Chi tiết: " + ex.getMessage());
            objDanhGia = null;
        }

        return  objDanhGia;
    }

    @Override
    public boolean themMoi(DanhGia obj) {

        DanhGia objDanhGia = danhGiaRepository.save(obj);

        if(objDanhGia != null){

            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DanhGia obj) {

        DanhGia objDanhGia = danhGiaRepository.findById(obj.getId()).get();

        if(objDanhGia != null){

            objDanhGia.setNguoiDg(obj.getNguoiDg());
            objDanhGia.setDiem(obj.getDiem());
            objDanhGia.setPhim(obj.getPhim());
            danhGiaRepository.save(objDanhGia);

            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DanhGia objDanhGia = layChiTietTheoMa(id);
        if(objDanhGia != null){
            danhGiaRepository.delete(objDanhGia);
            return true;
        }


        return false;
    }

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DanhGiaImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    public List<DanhGiaViewModel> layDanhSachView() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT dg.id,nd.ho_Ten, dg.diem,p.ten_Phim FROM Danh_Gia dg  \n");
        builder.append("INNER JOIN Nguoi_Dung nd ON dg.nguoi_Dg_Id = nd.id \n");
        builder.append("INNER JOIN Phim p ON dg.phim_ID = p.id \n ");

        return jdbcTemplate.query(builder.toString(), new BeanPropertyRowMapper<>(DanhGiaViewModel.class));
    }

    @Override
    public DanhGiaViewModel layDiemTheoPhimId(Integer id) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT  AVG(Sum(Diem)) as Diem FROM Danh_Gia dg  \n");
        builder.append("Where Phim_Id = :id GROUP BY Diem \n");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<DanhGiaViewModel> lstDanhGia = jdbcTemplate.query(builder.toString(),params, new BeanPropertyRowMapper<>(DanhGiaViewModel.class));
        if (lstDanhGia != null) {
            return lstDanhGia.get(0);
        }
        return null;
    }

    @Override
    public DanhGia getAllByNguoiDungAndPhimId(Integer nguoiDungId, Integer phimId) {
        return danhGiaRepository.getAllByNguoiDgAndPhim(nguoiDungId,phimId);
    }
}
