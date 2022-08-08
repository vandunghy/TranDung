package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import com.example.springboot_thymeleaf_phim.service.PhimDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class PhimApiController {

    @Autowired
    PhimDao phimDao;

    @GetMapping("phim/all")
    public ResponseEntity<List<Phim>> getAll(){

        List<Phim> lstPhim = phimDao.layDanhSach();

        return new ResponseEntity<List<Phim>>(lstPhim, HttpStatus.OK);

    }

    @GetMapping("phim/{id}")
    public ResponseEntity<?> getPhimId(@PathVariable("id") int id){

        if (phimDao.layChiTietTheoMa(id)==null){
            Messages apiErr = new Messages("Không thể lấy chi tiết phim có Id là:"+id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);

        } else {
            Phim objPhim = phimDao.layChiTietTheoMa(id);
            return new ResponseEntity<Phim>(objPhim,HttpStatus.OK);
        }
    }

    @PostMapping("phim/add")
    public ResponseEntity<?> addPhim(@RequestBody Phim objPhim){
        objPhim.setNgayTao(new Date());
        objPhim.setNgayCapNhat(new Date());
        objPhim.setNguoiDuyet(10);
        objPhim.setTrangThai(0);
        boolean ketQua = phimDao.themMoi(objPhim);

        if(ketQua)
        {
            return new ResponseEntity<Phim>(objPhim, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới phim");
        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }
    @PostMapping("phim/upload")
    public ResponseEntity<Object> fileUpload(@RequestParam("fUpload") MultipartFile fUpload) {
        String fileName = "";
        if (fUpload != null) {
            //Lấy tên file
            fileName = fUpload.getOriginalFilename();
            //Lấy đường dẫn upload trong file web.xml
            String strPath = "/Users/macbookm1/Documents/GitHub/JE112101HV/HocVien/Dungtv/springboot_thymeleaf_phim/src/main/resources/static/image";

            try {
                //Tạo file
                File file = new File(strPath, fileName);

                //Ghi ra file
                fUpload.transferTo(file);

            } catch (IOException e) {
                System.err.println("Có lỗi xảy ra trong quá trình upload file. Chi tiết: " + e.getMessage());
            }
        }
        return new ResponseEntity<Object>("Upload Successfully", HttpStatus.OK);
    }

    @PutMapping("phim/approve/{id}")
    public ResponseEntity<?> duyetPhim(@PathVariable("id") int id, HttpSession session) {
        Phim objPhim = phimDao.layChiTietTheoMa(id);
        if (objPhim == null) {
            Messages apiErr = new Messages("Không tìm thấy phim có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objPhim.setTrangThai(1);
            objPhim.setNguoiDuyet(Integer.parseInt(session.getAttribute("userId")+""));
            objPhim.setNgayDuyet(new Date());
            phimDao.capNhat(objPhim);
            Messages apiErr = new Messages("Duyệt thành công phim có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }

    @PutMapping("phim/disapprove/{id}")
    public ResponseEntity<?> huyDuyetSanPham(@PathVariable("id") String id, HttpSession session) {
        Phim objPhim = phimDao.layChiTietTheoMa(id);
        if (objPhim == null) {
            Messages apiErr = new Messages("Không tìm thấy phim có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objPhim.setTrangThai(0);
            objPhim.setNguoiDuyet(10);
            objPhim.setNgayDuyet(null);
            phimDao.capNhat(objPhim);
            Messages apiErr = new Messages("Duyệt thành công phim có id " + id);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }

    @PutMapping("phim/{id}")
    public ResponseEntity<?>updatePhim(@PathVariable("id")int id,@RequestBody Phim objPhim){
        Phim objPhimOld = phimDao.layChiTietTheoMa(id);
        if(objPhimOld == null){
            Messages apiErr = new Messages("Không thể tìm thấy phim có Id là:" +id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {

            objPhimOld.setTenPhim(objPhim.getTenPhim());
            objPhimOld.setMoTa(objPhim.getMoTa());
            objPhimOld.setNamSanXuat(objPhim.getNamSanXuat());
            objPhimOld.setThoiLuong(objPhim.getThoiLuong());
            objPhimOld.setNgayChieu(objPhim.getNgayChieu());
            objPhimOld.setQuocGia(objPhim.getQuocGia());
            objPhimOld.setDienVien(objPhim.getDienVien());
            objPhimOld.setDaoDien(objPhim.getDaoDien());
            objPhimOld.setTheLoai(objPhim.getTheLoai());
            objPhimOld.setNgayCapNhat(new Date());
            objPhimOld.setAnh(objPhim.getAnh());
            objPhimOld.setNoiDung(objPhim.getNoiDung());
            phimDao.capNhat(objPhimOld);
            return new ResponseEntity<Phim>(objPhimOld,HttpStatus.OK);
        }
    }
    @DeleteMapping("phim/{id}")
    public ResponseEntity<?> deletePhim(@PathVariable("id") int id){
        Phim objPhim = phimDao.layChiTietTheoMa(id);
        if (objPhim == null) {
            Messages apiErr = new Messages("Không tìm thấy Phim có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phimDao.xoa(id);
            Messages apiErr = new Messages("Xoá phim có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }

}
