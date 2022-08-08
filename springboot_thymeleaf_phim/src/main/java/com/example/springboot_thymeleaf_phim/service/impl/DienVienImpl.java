package com.example.springboot_thymeleaf_phim.service.impl;


import com.example.springboot_thymeleaf_phim.entity.DienVien;
import com.example.springboot_thymeleaf_phim.repository.DienVienRepository;
import com.example.springboot_thymeleaf_phim.service.DienVienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("dienVienDao")
public class DienVienImpl implements DienVienDao {
    @Autowired
    DienVienRepository dienVienRepository;

    @Override
    public List<DienVien> layDanhSach() {
        return (List<DienVien>)dienVienRepository.findAll();
    }

    @Override
    public DienVien layChiTietTheoMa(Object id) {
        DienVien objDienVien = null;
        try {
            objDienVien = dienVienRepository.findById(Integer.parseInt("" + id)).get();
        } catch (Exception ex) {
            System.out.println("Lỗi không lấy diễn viên có id: " + ex.getMessage());
        }
        return objDienVien;
    }

    @Override
    public boolean themMoi(DienVien obj) {
        DienVien objDienVien = dienVienRepository.save(obj);
        if (objDienVien != null) {

            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DienVien obj) {
        DienVien objDienVien = dienVienRepository.findById(obj.getId()).get();
        if (objDienVien != null) {
            objDienVien.setHoTen(obj.getHoTen());
            objDienVien.setGioiTinh(obj.getGioiTinh());
            dienVienRepository.save(objDienVien);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DienVien objDienVien = layChiTietTheoMa(id);

        if (objDienVien != null) {
            dienVienRepository.delete(objDienVien);
            return true;
        }
        return false;
    }

    @Override
    public List<DienVien> timKiemDienVien(String tuKhoa) {
        if(tuKhoa != null){
            return dienVienRepository.timKiemDienVien(tuKhoa);
        }
        return (List<DienVien>) dienVienRepository.findAll();
    }
}
