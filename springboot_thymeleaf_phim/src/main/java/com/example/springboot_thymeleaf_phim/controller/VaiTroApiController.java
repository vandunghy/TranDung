package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.VaiTro;
import com.example.springboot_thymeleaf_phim.service.VaiTroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class VaiTroApiController {

    @Autowired
    VaiTroDao vaiTroDao;

    @GetMapping("vaitro/all")
    public ResponseEntity<List<VaiTro>> getAll(){
        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();
        return new ResponseEntity<List<VaiTro>>(lstVaiTro, HttpStatus.OK);
    }

    @GetMapping("vaitro/{id}")
    public ResponseEntity<?> getVaiTroId(@PathVariable("id") int id){
        if(vaiTroDao.layChiTietTheoMa(id)==null){
            Messages apiErr = new Messages("Không thể lấy chi tiết quốc gia có id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            VaiTro objVaiTro = vaiTroDao.layChiTietTheoMa(id);

            return new ResponseEntity<VaiTro>(objVaiTro, HttpStatus.OK);
        }
    }

    @PostMapping("vaitro/add")
    public ResponseEntity<?>addVaiTro(@RequestBody VaiTro objVaiTro){

        boolean ketQua = vaiTroDao.themMoi(objVaiTro);

        if(ketQua){
            return new ResponseEntity<VaiTro>(objVaiTro, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới vai trò");

        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("vaitro/{id}")
    public ResponseEntity<?>updateQuoGia(@PathVariable("id")int id,@RequestBody VaiTro objVaiTro){
        VaiTro objVTGOld = vaiTroDao.layChiTietTheoMa(id);
        if(objVTGOld == null){
            Messages apiErr = new Messages("Không thể tìm vai trò có Id là:" +id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {

            objVTGOld.setTenVaiTro(objVaiTro.getTenVaiTro());
            objVTGOld.setMoTa(objVaiTro.getMoTa());
            vaiTroDao.capNhat(objVTGOld);

            return new ResponseEntity<VaiTro>(objVTGOld,HttpStatus.OK);
        }
    }
    @DeleteMapping("vaitro/{id}")
    public ResponseEntity<?> deleteVaiTro(@PathVariable("id") int id){
        VaiTro objVT = vaiTroDao.layChiTietTheoMa(id);
        if (objVT == null) {
            Messages apiErr = new Messages("Không tìm thấy vai trò có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            vaiTroDao.xoa(id);
            Messages apiErr = new Messages("Xoá vai trò có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
