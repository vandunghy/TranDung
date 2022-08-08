package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.ChucNang;
import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.service.ChucNangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ChucNangApiController {

    @Autowired
    ChucNangDao chucNangDao;

    @GetMapping("chucnang/all")
    public ResponseEntity<List<ChucNang>> getAll(){

        List<ChucNang>lstChucNang = chucNangDao.layDanhSach();
        return new ResponseEntity<List<ChucNang>>(lstChucNang, HttpStatus.OK);
    }

    @GetMapping("chucnang/{id}")
    public ResponseEntity<?> getChucNangById(@PathVariable ("id") int id){

        if(chucNangDao.layChiTietTheoMa(id) == null){
            Messages apiErr = new Messages("không thể lấy chi tiết chức năng có id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            ChucNang objChucNang = chucNangDao.layChiTietTheoMa(id);

            return new ResponseEntity<ChucNang>(objChucNang,HttpStatus.OK);
        }
    }

    @PostMapping("chucnang/add")
    public ResponseEntity<?> chucNangAdd(@RequestBody ChucNang objChucNang){

        boolean ketQua = chucNangDao.themMoi(objChucNang);

        if (ketQua){
            return new ResponseEntity<ChucNang>(objChucNang,HttpStatus.OK);
        }
        Messages apiErr = new Messages("không thể thêm mới chức năng :");
        return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
    }

    @PutMapping("chucnang/{id}")
    public ResponseEntity<?> updateChucNang(@PathVariable ("id") int id,@RequestBody ChucNang objCN){

        ChucNang objCNOld = chucNangDao.layChiTietTheoMa(id);
        if(objCNOld == null){
            Messages apiErr = new Messages("Không tìm thấy chức năng ó id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            objCNOld.setTenChucNang(objCN.getTenChucNang());
            objCNOld.setMoTa(objCN.getMoTa());
            objCNOld.setTenFrom(objCN.getTenFrom());
            objCNOld.setModule(objCN.getModule());
            objCNOld.setOderNumber(objCN.getOderNumber());
            chucNangDao.capNhat(objCNOld);
            return new ResponseEntity<ChucNang>(objCNOld,HttpStatus.OK);
        }
    }

    @DeleteMapping("chucnang/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){

        if (chucNangDao.layChiTietTheoMa(id) == null) {
            Messages apiErr = new Messages("Không tìm thấy chức năng có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            chucNangDao.xoa(id);
            Messages apiErr = new Messages("Xoá chức năng có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }

}
