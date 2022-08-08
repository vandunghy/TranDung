package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.DaoDien;
import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.service.DaoDienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class DaoDienApiController {

    @Autowired
    DaoDienDao daoDienDao;

    @GetMapping("daodien/all")
    public ResponseEntity<List<DaoDien>> getAll()
    {
        List<DaoDien> lstDaoDien = daoDienDao.layDanhSach();
        return new ResponseEntity<List<DaoDien>>(lstDaoDien, HttpStatus.OK);
    }

    @GetMapping("daodien/{id}")
    public ResponseEntity<?> getDaoDienById(@PathVariable("id") int id)
    {
        if (daoDienDao.layChiTietTheoMa(id) ==null)
        {
            Messages apiErr  = new Messages("Không thể lấy danh sách đạo diễn có Id"+id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {
            DaoDien objDaoDien = daoDienDao.layChiTietTheoMa(id);
            return new ResponseEntity<DaoDien>(objDaoDien, HttpStatus.OK);
        }
    }

    @PostMapping("daodien/add")
    public ResponseEntity<?> addDaoDien(@RequestBody DaoDien objDaoDien) {
        boolean kQ = daoDienDao.themMoi(objDaoDien);
        if (kQ) {
            return new ResponseEntity<DaoDien>(objDaoDien, HttpStatus.OK);
        }
        Messages apiErr = new Messages("không thể tạo đạo diễn");
        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("daodien/{id}")
    public ResponseEntity<?> updateDaoDien(@PathVariable("id") int id, @RequestBody DaoDien objDaoDien) {
        DaoDien objDaoDienOld = daoDienDao.layChiTietTheoMa(id);
        if (objDaoDienOld == null) {
            Messages apiErr = new Messages("Không tìm thấy đạo diễn có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDaoDienOld.setHoTen(objDaoDien.getHoTen());
            objDaoDienOld.setGioiTinh(objDaoDien.getGioiTinh());
            daoDienDao.capNhat(objDaoDienOld);
            return new ResponseEntity<DaoDien>(objDaoDienOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("daodien/{id}")
    public ResponseEntity<?> deleteDaoDien(@PathVariable("id") int id) {
        DaoDien objDaoDien = daoDienDao.layChiTietTheoMa(id);
        if (objDaoDien == null) {
            Messages apiErr = new Messages("Không tìm thấy đạo diễn có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            daoDienDao.xoa(id);
            Messages apiErr = new Messages("Xoá đạo diễn có id: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
