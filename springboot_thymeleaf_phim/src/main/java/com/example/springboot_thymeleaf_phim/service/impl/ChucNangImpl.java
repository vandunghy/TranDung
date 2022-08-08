package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.repository.ChucNangRepository;
import com.example.springboot_thymeleaf_phim.service.ChucNangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("chucNangDao")
public class ChucNangImpl implements ChucNangDao {

    @Autowired
    ChucNangRepository chucNangRepository;

    @Override
    public List<ChucNang> layDanhSach() {
        return (List<ChucNang>) chucNangRepository.findAll() ;
    }

    @Override
    public ChucNang layChiTietTheoMa(Object id) {
        ChucNang objChucNang = null;

        try{
            objChucNang = chucNangRepository.findById(Integer.parseInt(""+id)).get();

        }catch (Exception ex){

            System.out.println("không lấy được chức năng .Chi tiêt" +ex.getMessage());
            objChucNang = null;

        }

        return objChucNang;
    }

    @Override
    public boolean themMoi(ChucNang obj) {

        ChucNang objChucNang = chucNangRepository.save(obj);

        if(objChucNang != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(ChucNang obj) {

        ChucNang objChucNang = chucNangRepository.findById(obj.getId()).get();

        if(objChucNang != null){

            objChucNang.setTenChucNang(obj.getTenChucNang());
            objChucNang.setMoTa(obj.getMoTa());
            objChucNang.setTenFrom(obj.getTenFrom());
            objChucNang.setModule(obj.getModule());
            objChucNang.setOderNumber(obj.getOderNumber());
            chucNangRepository.save(objChucNang);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {

        ChucNang objChucNang = layChiTietTheoMa(id);
        if( objChucNang != null){
            chucNangRepository.deleteAll();
            return true;
        }
        return false;
    }

    @Override
    public List<ChucNang> timKiemChucNang(String tuKhoa) {
        if(tuKhoa != null){

            return chucNangRepository.timKiemChucNang(tuKhoa);
        }
        return (List<ChucNang>) chucNangRepository.findAll();
    }
}
