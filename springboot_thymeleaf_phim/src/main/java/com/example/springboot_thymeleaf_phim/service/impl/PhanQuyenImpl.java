package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import com.example.springboot_thymeleaf_phim.repository.PhanQuyenRepository;
import com.example.springboot_thymeleaf_phim.service.PhanQuyenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phanQuyenDao")
public class PhanQuyenImpl implements PhanQuyenDao {
    @Autowired
    PhanQuyenRepository phanQuyenRepository;

    @Override
    public List<PhanQuyen> layDanhSach() {
        return (List<PhanQuyen>) phanQuyenRepository.findAll();
    }

    @Override
    public PhanQuyen layChiTietTheoMa(Object id) {
        PhanQuyen objPhanQuyen = null;

        try{
            objPhanQuyen = phanQuyenRepository.findById(Integer.parseInt(""+id)).get();


        }catch (Exception ex){

            System.out.println("Không lấy đc chi tiết "+ex.getMessage());
            objPhanQuyen=null;
        }
        return objPhanQuyen;
    }

    @Override
    public boolean themMoi(PhanQuyen obj) {
        PhanQuyen objPhanQuyen = phanQuyenRepository.save(obj);

        if (objPhanQuyen !=null ){

            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(PhanQuyen obj) {
        PhanQuyen objPhanQuyen = phanQuyenRepository.findById(obj.getId()).get();

        if(objPhanQuyen != null){

            objPhanQuyen.setChucNang(obj.getChucNang());
            objPhanQuyen.setVaiTro(obj.getVaiTro());
            objPhanQuyen.setXem(obj.getXem());
            objPhanQuyen.setThem(obj.getThem());
            objPhanQuyen.setSua(obj.getSua());
            objPhanQuyen.setXoa(obj.getXoa());
            objPhanQuyen.setBaoCao(obj.getBaoCao());
            phanQuyenRepository.save(objPhanQuyen);
            return true;

        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        PhanQuyen objPhanQuyen = layChiTietTheoMa(id);

        if(objPhanQuyen != null){

            phanQuyenRepository.delete(objPhanQuyen);

            return true;
        }
        return false;
    }

    @Override
    public PhanQuyen layChiTietTheoVaiTro_ChucNang(int vaiTroId, int chucNangId) {
        PhanQuyen objPhanQuyen = null;
        try {
            objPhanQuyen = phanQuyenRepository.findByVaiTroAndChucNang(vaiTroId, chucNangId);
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objPhanQuyen = null;
        }
        return objPhanQuyen;
    }

    @Override
    public boolean xoaTheoVaiTro_ChucNang(int vaiTroId, int chucNangId) {
        PhanQuyen objPhanQuyen = layChiTietTheoVaiTro_ChucNang(vaiTroId, chucNangId);
        if (objPhanQuyen != null) {
            phanQuyenRepository.delete(objPhanQuyen);
            return true;
        }
        return false;

    }

    @Override
    public List<PhanQuyen> layChiTietTheoVaiTroId(int id) {
        return phanQuyenRepository.findByVaiTro(id);
    }

    @Override
    public List<PhanQuyen> listQuyenChuaPhan(List<Integer> lstChucNangId) {
        return phanQuyenRepository.findByChucNangNotIn(lstChucNangId);
    }
}
