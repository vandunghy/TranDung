package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.repository.TheLoaiRepository;
import com.example.springboot_thymeleaf_phim.service.TheLoaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("theLoaiDao")
public class TheLoaiImpl implements TheLoaiDao {
    @Autowired
    TheLoaiRepository theLoaiRepository;

    @Override
    public List<TheLoai> layDanhSach() {
        return (List<TheLoai>)theLoaiRepository.findAll();
    }

    @Override
    public TheLoai layChiTietTheoMa(Object id) {
        TheLoai objTheLoai = null;
        try {
            objTheLoai = theLoaiRepository.findById(Integer.parseInt(""+id)).get();
        }catch (Exception ex)
        {
            System.out.println("Lỗi không lấy được thể loại phim"+ ex.getMessage());
        }
        return objTheLoai;
    }

    @Override
    public boolean themMoi(TheLoai obj) {
        TheLoai objtheLoai = theLoaiRepository.save(obj);
        if (objtheLoai != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(TheLoai obj) {
        TheLoai objTheLoai = theLoaiRepository.findById(obj.getId()).get();
        if (objTheLoai != null)
        {
            objTheLoai.setTenTheLoai(obj.getTenTheLoai());
            theLoaiRepository.save(objTheLoai);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        TheLoai objTheLoai = layChiTietTheoMa(id);
        if (objTheLoai != null)
        {
            theLoaiRepository.delete(objTheLoai);
            return true;
        }
        return false;
    }

    @Override
    public List<TheLoai> timKiemTheLoai(String tuKhoa) {
        if (tuKhoa != null ) {
            return theLoaiRepository.timKiemTheLoai(tuKhoa);
        }
        return (List<TheLoai>) theLoaiRepository.findAll();
    }

}
