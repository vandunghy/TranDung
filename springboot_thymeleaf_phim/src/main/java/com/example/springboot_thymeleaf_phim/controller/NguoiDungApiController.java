package com.example.springboot_thymeleaf_phim.controller;



import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.entity.NguoiDungViewModel;
import com.example.springboot_thymeleaf_phim.service.impl.NguoidungRepositoryView;
import com.example.springboot_thymeleaf_phim.service.NguoiDungDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class NguoiDungApiController {

    @Autowired
    NguoiDungDao nguoiDungDao;

    @Autowired
    NguoidungRepositoryView nguoidungRepositoryView;

    @GetMapping("user/all")
    public ResponseEntity<List<NguoiDung>> getAll(){
        List<NguoiDung> listStudent = nguoiDungDao.layDanhSach();
        return new ResponseEntity<List<NguoiDung>>(listStudent, HttpStatus.OK);
    }
    @GetMapping("user/all1")
    public ResponseEntity<List<NguoiDungViewModel>> getAll1(){
        List<NguoiDungViewModel> listStudent = nguoidungRepositoryView.layDanhSach();
        return new ResponseEntity<List<NguoiDungViewModel>>(listStudent, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id){
        if (nguoiDungDao.layChiTietTheoMa(id) == null) {
            Messages apiErr = new Messages("Không thể tạo người dùng có ID: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            NguoiDung objUser = nguoiDungDao.layChiTietTheoMa(id);
            return new ResponseEntity<NguoiDung>(objUser, HttpStatus.OK);
        }
    }

    @PostMapping("user/add")
    public ResponseEntity<?> addUser(@RequestBody NguoiDung objUser){
        objUser.setNgayTao(new Date());
        boolean ketQua = nguoiDungDao.themMoi(objUser);
        if (ketQua) {
            return new ResponseEntity<NguoiDung>(objUser, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới người dùng");

        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody NguoiDung objUser){
        NguoiDung objUserOld = nguoiDungDao.layChiTietTheoMa(id);
        if (objUserOld == null) {
            Messages apiErr = new Messages("Không tìm thấy người dùng có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objUserOld.setTenDangNhap(objUser.getTenDangNhap());
            objUserOld.setHoTen(objUser.getHoTen());
            objUserOld.setDienThoai(objUser.getDienThoai());
            objUserOld.setEmail(objUser.getEmail());
            objUserOld.setDiaChi(objUser.getDiaChi());
            objUserOld.setVaiTro(objUser.getVaiTro());
            nguoiDungDao.capNhat(objUserOld);
            return new ResponseEntity<NguoiDung>(objUserOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){
        NguoiDung objUser = nguoiDungDao.layChiTietTheoMa(id);
        if (objUser == null) {
            Messages apiErr = new Messages("Không tìm thấy người dùng có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            nguoiDungDao.xoa(id);
            Messages apiErr = new Messages("Xoá người dùng có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
