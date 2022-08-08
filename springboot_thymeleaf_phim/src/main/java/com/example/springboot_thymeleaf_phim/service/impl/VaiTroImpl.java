package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import com.example.springboot_thymeleaf_phim.repository.VaiTroRepository;
import com.example.springboot_thymeleaf_phim.service.VaiTroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("vaiTroDao")
public class VaiTroImpl implements VaiTroDao {

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Override
    public List<VaiTro> layDanhSach() {
        return (List<VaiTro>) vaiTroRepository.findAll();
    }

    @Override
    public VaiTro layChiTietTheoMa(Object id) {

        VaiTro objVaiTro = null;
        try{
            objVaiTro = vaiTroRepository.findById(Integer.parseInt(""+id)).get();

        }catch (Exception ex){
            System.out.println("Không lấy được chi tiết vai trò:"+ex.getMessage());
            objVaiTro = null;
        }
        return objVaiTro;
    }

    @Override
    public boolean themMoi(VaiTro obj) {

        VaiTro objVaiTro = vaiTroRepository.save(obj);

        if(objVaiTro !=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(VaiTro obj) {
        VaiTro objVaiTro = vaiTroRepository.findById(obj.getId()).get();

        if(objVaiTro !=null){

            objVaiTro.setTenVaiTro(obj.getTenVaiTro());
            objVaiTro.setMoTa(obj.getMoTa());
            vaiTroRepository.save(objVaiTro);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        VaiTro objVaiTro = layChiTietTheoMa(id);

        if(objVaiTro != null){

            vaiTroRepository.delete(objVaiTro);

            return true;
        }
        return false;
    }

    @Override
    public List<VaiTro> timKiemVaiTro(String tuKhoa) {
        if(tuKhoa != null ) {
            return vaiTroRepository.timKiemVaiTro(tuKhoa);
        }
        return (List<VaiTro>) vaiTroRepository.findAll();
    }


}
