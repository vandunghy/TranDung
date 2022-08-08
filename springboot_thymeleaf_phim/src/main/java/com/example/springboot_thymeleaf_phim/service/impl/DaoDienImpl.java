package com.example.springboot_thymeleaf_phim.service.impl;



import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.repository.DaoDienRepository;
import com.example.springboot_thymeleaf_phim.service.DaoDienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("daoDienDao")
public class DaoDienImpl implements DaoDienDao {
    @Autowired
    DaoDienRepository daoDienRepository;

    @Override
    public List<DaoDien> layDanhSach() {
        return (List<DaoDien>)daoDienRepository.findAll();
    }

    @Override
    public DaoDien layChiTietTheoMa(Object id) {
        DaoDien objDaoDien = null;
        try {
            objDaoDien = daoDienRepository.findById(Integer.parseInt(""+id)).get();
        }catch (Exception ex)
        {
            System.out.println("Không lấy được đạo diễn phim có id:" + ex.getMessage());
            objDaoDien = null;
        }
        return objDaoDien;
    }

    @Override
    public boolean themMoi(DaoDien obj) {
        DaoDien objDaoDien = daoDienRepository.save(obj);
        if (objDaoDien != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DaoDien obj) {
        DaoDien objDaoDien = daoDienRepository.findById(obj.getId()).get();
        if (objDaoDien != null)
        {
            objDaoDien.setHoTen(obj.getHoTen());
            objDaoDien.setGioiTinh(obj.getGioiTinh());
            daoDienRepository.save(objDaoDien);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DaoDien objDaoDien = layChiTietTheoMa(id);
        if (objDaoDien != null)
        {
            daoDienRepository.delete(objDaoDien);
            return true;
        }
        return false;
    }

    @Override
    public List<DaoDien> timKiemDaoDien(String tuKhoa) {
        if(tuKhoa != null){

            return daoDienRepository.timKiemDaoDien(tuKhoa);
        }
        return (List<DaoDien>) daoDienRepository.findAll();
    }
}
