package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Anh;
import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.service.AnhDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api")
public class AnhApiController {

    @Autowired
    AnhDao anhDao;

    @GetMapping("anh/all")
    public ResponseEntity<List<Anh>> getAll(){

        List<Anh> lstAnh = anhDao.layDanhSach();

        return new  ResponseEntity<List<Anh>>(lstAnh, HttpStatus.OK);
    }

    @GetMapping("anh/{id}")
    public ResponseEntity<?> getAnhById(@PathVariable ("id") int id){

        if (anhDao.layChiTietTheoMa(id)==null){
            Messages apiErr = new Messages("Không thể lấy chi tiết ảnh có Id là:"+id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);

        } else {
            Anh objAnh = anhDao.layChiTietTheoMa(id);
            return new ResponseEntity<Anh>(objAnh,HttpStatus.OK);
        }
    }

    @GetMapping("anh/dsanh/{id}")
    public ResponseEntity<?>layChiTietTheoPhimId(@PathVariable("id") int id){
        if(anhDao.layDanhSachAnhTheoPhimId(id) ==null){
            Messages apiErr = new Messages("Không thể lấy chi tiết ảnh có Id là:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            List<Anh> lstAnh = anhDao.layDanhSachAnhTheoPhimId(id);
            return new ResponseEntity<List<Anh>>(lstAnh,HttpStatus.OK);
        }
    }

    @PostMapping("anh/add")
    public ResponseEntity<?> addAnh(@RequestBody Anh objAnh){

        boolean ketQua = anhDao.themMoi(objAnh);

        if(ketQua)
        {
            return new ResponseEntity<Anh>(objAnh, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới ảnh");
        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }
    @PostMapping("anh/upload")
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

    @PutMapping("anh/{id}")
    public ResponseEntity<?>updateAnh(@PathVariable("id")int id,@RequestBody Anh objAnh){
        Anh objAnhOld = anhDao.layChiTietTheoMa(id);
        if(objAnhOld == null){
            Messages apiErr = new Messages("Không thể tìm thấy Ảnh có Id là:" +id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {

            objAnhOld.setTenAnh(objAnh.getTenAnh());
            objAnhOld.setMoTa(objAnh.getMoTa());
            objAnhOld.setPhim(objAnh.getPhim());
            anhDao.capNhat(objAnhOld);
            return new ResponseEntity<Anh>(objAnhOld,HttpStatus.OK);
        }
    }
    @DeleteMapping("anh/{id}")
    public ResponseEntity<?> deleteAnh(@PathVariable("id") int id){
        Anh objAnh = anhDao.layChiTietTheoMa(id);
        if (objAnh == null) {
            Messages apiErr = new Messages("Không tìm thấy ảnh có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            anhDao.xoa(id);
            Messages apiErr = new Messages("Xoá ảnh có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }

    @DeleteMapping("anh/{phimId}/{tenAnh}")
    public ResponseEntity<?> xoaAnhTheoPhimVaTen(@PathVariable("phimId") Integer phimId, @PathVariable("tenAnh") String tenAnh) {
        Anh objAnh = anhDao.findByPhimAndTen(phimId, tenAnh);
        if (objAnh == null) {
            Messages apiErr = new Messages("Không tìm thấy ảnh có mã sản phẩm " + phimId + " và tên ảnh " + tenAnh);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            anhDao.xoaTheoPhimIDVaTen(phimId, tenAnh);
            Messages apiErr = new Messages("Xóa ảnh có mã sản phẩm " + phimId + " và tên ảnh " + tenAnh);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
