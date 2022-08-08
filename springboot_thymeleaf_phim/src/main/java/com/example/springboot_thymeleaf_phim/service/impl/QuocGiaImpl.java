package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.repository.QuocGiaReponstory;
import com.example.springboot_thymeleaf_phim.service.QuocGiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("quocGiaDao")
public class QuocGiaImpl implements QuocGiaDao {

    @Autowired
    QuocGiaReponstory quocGiaReponstory;

    @Override
    public List<QuocGia> layDanhSach() {
        return (List<QuocGia>) quocGiaReponstory.findAll() ;
    }

    @Override
    public QuocGia layChiTietTheoMa(Object id) {
        QuocGia objQuocGia = null;

        try{
            objQuocGia = quocGiaReponstory.findById(Integer.parseInt("" +id)).get();
        }catch (Exception ex){
            System.out.println("Không thể lấy chi tiết quốc gia" +ex.getMessage());
            objQuocGia = null;
        }

        return objQuocGia;
    }

    @Override
    public boolean themMoi(QuocGia obj) {
        QuocGia objQuocGia = quocGiaReponstory.save(obj);

        if(objQuocGia != null){

            return true;
        }

        return false;
    }

    @Override
    public boolean capNhat(QuocGia obj) {
        QuocGia objQuocGia = quocGiaReponstory.findById(obj.getId()).get();

        if(objQuocGia != null){

            objQuocGia.setMaQuocGia(obj.getMaQuocGia());
            objQuocGia.setTenQuocGia(obj.getTenQuocGia());
            quocGiaReponstory.save(objQuocGia);
            return true;

        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        QuocGia objQuocGia = layChiTietTheoMa(id);

        if(objQuocGia != null){

            quocGiaReponstory.delete(objQuocGia);

            return true;
        }
        return false;
    }

    @Override
    public List<QuocGia> timKiemQuocGia(String tuKhoa) {
        if (tuKhoa != null ) {
            return quocGiaReponstory.timKiemQuocGia(tuKhoa);
        }
        return (List<QuocGia>) quocGiaReponstory.findAll();
    }
}
