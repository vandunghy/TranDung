package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.TheLoai;
import com.example.springboot_thymeleaf_phim.service.TheLoaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TheLoaiApiController {
    @Autowired
    TheLoaiDao theLoaiDao;

    @GetMapping("theloai/all")
    public ResponseEntity<List<TheLoai>> getAll() {
        List<TheLoai> lstSt = theLoaiDao.layDanhSach();
        return new ResponseEntity<List<TheLoai>>(lstSt, HttpStatus.OK);
    }

    @GetMapping("theloai/{id}")
    public ResponseEntity<?> getTheLoaiById(@PathVariable("id") int id) {
        if (theLoaiDao.layChiTietTheoMa(id) == null) {
            Messages apiErr = new Messages("không thể thể loại ID" + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            TheLoai objTheLoai = theLoaiDao.layChiTietTheoMa(id);
            return new ResponseEntity<TheLoai>(objTheLoai, HttpStatus.OK);
        }
    }

    @PostMapping("theloai/add")
    public ResponseEntity<?> addTheLoai(@RequestBody TheLoai objTheLoai) {
        boolean kQ = theLoaiDao.themMoi(objTheLoai);
        if (kQ) {
            return new ResponseEntity<TheLoai>(objTheLoai, HttpStatus.OK);
        }
        Messages apiErr = new Messages("không thể tạo thể loại phim");
        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("theloai/{id}")
    public ResponseEntity<?> updateTheLoai(@PathVariable("id") int id, @RequestBody TheLoai objTheLoai) {
        TheLoai objTheLoaiOld = theLoaiDao.layChiTietTheoMa(id);
        if (objTheLoaiOld == null) {
            Messages apiErr = new Messages("Không tìm thấy thể loại có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objTheLoaiOld.setTenTheLoai(objTheLoai.getTenTheLoai());

            theLoaiDao.capNhat(objTheLoaiOld);
            return new ResponseEntity<TheLoai>(objTheLoaiOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("theloai/{id}")
    public ResponseEntity<?> deleteTheLoai(@PathVariable("id") int id) {
        TheLoai objTheLoai = theLoaiDao.layChiTietTheoMa(id);
        if (objTheLoai == null) {
            Messages apiErr = new Messages("Không tìm thấy thể loại có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            theLoaiDao.xoa(id);
            Messages apiErr = new Messages("Xoá thể loại có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
