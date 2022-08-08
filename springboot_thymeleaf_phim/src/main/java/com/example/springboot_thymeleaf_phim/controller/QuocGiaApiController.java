package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.QuocGia;
import com.example.springboot_thymeleaf_phim.service.QuocGiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class QuocGiaApiController {

    @Autowired
    QuocGiaDao quocGiaDao;


    @GetMapping("quocgia/all")
    public ResponseEntity<List<QuocGia>> getAll(){
        List<QuocGia> lstQuocGia = quocGiaDao.layDanhSach();
        return new ResponseEntity<List<QuocGia>>(lstQuocGia, HttpStatus.OK);
    }

    @GetMapping("quocgia/{id}")
    public ResponseEntity<?> getQuoGiaId(@PathVariable("id") int id){
        if(quocGiaDao.layChiTietTheoMa(id)==null){
            Messages apiErr = new Messages("Không thể lấy chi tiết quốc gia có id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            QuocGia objQuocGia = quocGiaDao.layChiTietTheoMa(id);

            return new ResponseEntity<QuocGia>(objQuocGia, HttpStatus.OK);
        }
    }

    @PostMapping("quocgia/add")
    public ResponseEntity<?>addQuoGia(@RequestBody QuocGia objQuocGia){

        boolean ketQua = quocGiaDao.themMoi(objQuocGia);

        if(ketQua){
            return new ResponseEntity<QuocGia>(objQuocGia, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới quốc gia");

        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("quocgia/{id}")
    public ResponseEntity<?>updateQuoGia(@PathVariable("id")int id,@RequestBody QuocGia objQuocGia){
        QuocGia objQGOld = quocGiaDao.layChiTietTheoMa(id);
        if(objQGOld == null){
            Messages apiErr = new Messages("Không thể tìm quốc gia có Id là:" +id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {

            objQGOld.setMaQuocGia(objQuocGia.getMaQuocGia());
            objQGOld.setTenQuocGia(objQuocGia.getTenQuocGia());
            quocGiaDao.capNhat(objQGOld);

            return new ResponseEntity<QuocGia>(objQGOld,HttpStatus.OK);
        }
    }
    @DeleteMapping("quocgia/{id}")
    public ResponseEntity<?> deleteQuoGia(@PathVariable("id") int id){
        QuocGia objQG = quocGiaDao.layChiTietTheoMa(id);
        if (objQG == null) {
            Messages apiErr = new Messages("Không tìm thấy quốc gia có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            quocGiaDao.xoa(id);
            Messages apiErr = new Messages("Xoá quốc gia có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
