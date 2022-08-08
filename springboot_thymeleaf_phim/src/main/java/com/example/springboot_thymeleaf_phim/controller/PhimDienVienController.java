package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.PhimDienVien;
import com.example.springboot_thymeleaf_phim.service.PhimDienVienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phim-dien-vien")
public class PhimDienVienController {
    @Autowired
    PhimDienVienDao phimDienVienDao;

    @GetMapping("/lay-danh-sach")
    public ResponseEntity<List<PhimDienVien>> getAll()
    {
        List<PhimDienVien> lstPhimDienVien = phimDienVienDao.layDanhSach();
        return new ResponseEntity<List<PhimDienVien>>(lstPhimDienVien, HttpStatus.OK);
    }

    @GetMapping("/chi-tiet/{id}")
    public ResponseEntity<?> getPhimDienVienById(@PathVariable("id") int id)
    {
        if (phimDienVienDao.layChiTietTheoMa(id) ==null)
        {
            Messages apiErr  = new Messages("Không thể lấy danh sách phim diễn viên có Id"+id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {
            PhimDienVien objPhimDienVien = phimDienVienDao.layChiTietTheoMa(id);
            return new ResponseEntity<PhimDienVien>(objPhimDienVien, HttpStatus.OK);
        }
    }

    @PostMapping("/them-moi")
    public ResponseEntity<?> addPhimDienVien(@RequestBody PhimDienVien objPhimDv) {
        boolean kQ = phimDienVienDao.themMoi(objPhimDv);
        if (kQ) {
            return new ResponseEntity<PhimDienVien>(objPhimDv, HttpStatus.OK);
        }
        Messages apiErr = new Messages("không thể tạo Phim - diễn viên");
        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cap-nhat/{id}")
    public ResponseEntity<?> updatePhimDienVien(@PathVariable("id") int id, @RequestBody PhimDienVien objPhimDV) {
        PhimDienVien objPhimDVOld = phimDienVienDao.layChiTietTheoMa(id);
        if (objPhimDVOld == null) {
            Messages apiErr = new Messages("Không tìm thấy phim - diễn viên có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objPhimDVOld.setHoTen(objPhimDV.getHoTen());
            objPhimDVOld.setVaiDien(objPhimDV.getVaiDien());
            objPhimDVOld.setPhimId(objPhimDV.getPhimId());
            objPhimDVOld.setDienVien(objPhimDV.getDienVien());
            phimDienVienDao.capNhat(objPhimDVOld);
            return new ResponseEntity<PhimDienVien>(objPhimDVOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("/xoa/{id}")
    public ResponseEntity<?> deletePhimDienVien(@PathVariable("id") int id) {
        PhimDienVien objPhimDienVien = phimDienVienDao.layChiTietTheoMa(id);
        if (objPhimDienVien == null) {
            Messages apiErr = new Messages("Không tìm thấy phim - diễn viên có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phimDienVienDao.xoa(id);
            Messages apiErr = new Messages("Xoá phim - diễn viên có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
