package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.*;
import com.example.springboot_thymeleaf_phim.repository.PhimYeuThichRepository;
import com.example.springboot_thymeleaf_phim.service.PhimDienVienDao;
import com.example.springboot_thymeleaf_phim.service.PhimYeuThichDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("phimyeuThichDao")
public class PhimYeuThichImpl implements PhimYeuThichDao {

    @Autowired
    PhimYeuThichRepository phimYeuThichRepository;


    @Override
    public List<PhimYeuThich> layDanhSach() {
        return  (List<PhimYeuThich>) phimYeuThichRepository.findAll();
    }

    @Override
    public PhimYeuThich layChiTietTheoMa(Object id) {
        PhimYeuThich objPhimYeuThich = null;
        try {
            objPhimYeuThich = phimYeuThichRepository.findById(Integer.parseInt(""+id)).get();
        }catch (Exception ex){
            System.out.println("Khôn thể lấy chi tiết Phim Yêu Thích"+ex.getMessage());
        }
        return objPhimYeuThich;
    }

    @Override
    public boolean themMoi(PhimYeuThich obj) {

        List<PhimYeuThich> lstPhimOld = phimYeuThichRepository.getAllByNguoiDungAndPhimId(obj.getNguoiDung(),obj.getPhimId());

        if(lstPhimOld.size()==0){

        PhimYeuThich objPhimYeuThich = phimYeuThichRepository.save(obj);

        if(objPhimYeuThich != null){
            return true;
          }
        }
        return false;
    }

    @Override
    public boolean capNhat(PhimYeuThich obj) {

        PhimYeuThich objPhimyeuThich = phimYeuThichRepository.findById(obj.getId()).get();
        if (objPhimyeuThich != null){

            objPhimyeuThich.setPhimId(obj.getPhimId());
            objPhimyeuThich.setNguoiDung(obj.getNguoiDung());
            phimYeuThichRepository.save(objPhimyeuThich);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {

        PhimYeuThich objPhimYeuThich = layChiTietTheoMa(id);
        if(objPhimYeuThich != null){
            phimYeuThichRepository.delete(objPhimYeuThich);
            return true;
        }
        return false;
    }

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PhimYeuThichImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<PhimViewModel> getPhimYeuThichByNguoiDung(Integer nguoiDungId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT pyt.id as phimYTID, p.ID,p.Anh, p.Ten_Phim,p.Mo_ta,p.Trang_Thai, p.Nam_San_Xuat,\n");
        builder.append("p.Thoi_Luong, p.Ngay_Chieu, dv.Ho_Ten as Ten_Dien_Vien,\n");
        builder.append("dd.ho_Ten as Ten_Dao_Dien, tl.ten_The_Loai,  \n");
        builder.append("(SELECT AVG(SUM(Diem)) FROM DANH_GIA where Phim_Id = p.id GROUP BY DIEM) as Diem From phim_Yeu_Thich pyt \n");
        builder.append("LEFT JOIN  Phim p on pyt.Phim_Id = p.id  \n");
        builder.append("LEFT JOIN The_Loai tl ON p.The_Loai_ID = tl.id \n");
        builder.append("LEFT JOIN Dao_Dien dd ON p.Dao_Dien_ID = dd.id \n");
        builder.append("LEFT JOIN Dien_Vien dv ON p.Dien_Vien_ID = dv.id  \n");
        if (nguoiDungId != null) {
            builder.append("Where pyt.nguoi_dung_id = :nguoiDungId \n");
        }


        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nguoiDungId", nguoiDungId);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(PhimViewModel.class));
    }

}
