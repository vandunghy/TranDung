package com.example.springboot_thymeleaf_phim.service.impl;


import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.NguoiDungViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("nguoidungRepositoryView")
public class NguoidungRepositoryView {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public NguoidungRepositoryView(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Autowired
    EntityManager entityManager;

    public NguoidungRepositoryView() {
        // create an instance of entity manager factory
    }

    public List<NguoiDungViewModel> layDanhSach() {

        List<NguoiDungViewModel> lstNguoiDung = new ArrayList<>();

        Query query = entityManager
                .createQuery("SELECT  u.id, u.tenDangNhap as tenDangNhap,u.hoTen,u.dienThoai,u.email,u.diaChi,u.ngayTao,vt.tenVaiTro from NguoiDung u JOIN VaiTro vt ON u.vaiTro=vt.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] r : lstObject) {
            NguoiDungViewModel objUser = new NguoiDungViewModel();

            objUser.setId(Integer.parseInt("" + r[0]));
            objUser.setTenDangNhap("" + r[1]);
            objUser.setHoTen("" + r[2]);
            objUser.setDienThoai("" + r[3]);
            objUser.setEmail("" + r[4]);
            objUser.setDiaChi("" + r[5]);
            objUser.setNgayTao((Date) r[6]);
            objUser.setTenVaiTro("" + r[7]);
            lstNguoiDung.add(objUser);
        }
        return lstNguoiDung;
    }

    public List<NguoiDungViewModel> timKiemNguoiDung(String tuKhoa, Integer vaiTro) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT u.id, u.ten_Dang_Nhap ,u.ho_Ten,u.dien_Thoai, u.email, u.dia_Chi, u.ngay_Tao, vt.ten_Vai_Tro FROM NGUOI_DUNG u JOIN Vai_Tro vt ON u.vai_Tro_Id = vt.id \n");
        builder.append("WHERE (HO_TEN LIKE '%' || :tuKhoa || '%' \n");
        builder.append("OR TEN_DANG_NHAP LIKE '%' || :tuKhoa || '%' \n ");
        builder.append("OR DIEN_THOAI LIKE '%' || :tuKhoa || '%' \n");
        builder.append("OR DIA_CHI LIKE '%' || :tuKhoa || '%') \n");
        if (vaiTro != null) {
            builder.append("AND VAI_TRO_ID = :vaiTro \n");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("vaiTro", vaiTro);

        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(NguoiDungViewModel.class));
    }
}
