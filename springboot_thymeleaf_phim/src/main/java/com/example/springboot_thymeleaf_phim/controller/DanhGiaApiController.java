package com.example.springboot_thymeleaf_phim.controller;


import com.example.springboot_thymeleaf_phim.entity.DanhGia;
import com.example.springboot_thymeleaf_phim.entity.DanhGiaViewModel;
import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.service.DanhGiaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("api")
public class DanhGiaApiController {

    @Autowired
    DanhGiaDao danhGiaDao;

    @GetMapping("danhgia/all")
    public ResponseEntity<List<DanhGiaViewModel>> getAll(){
        List<DanhGiaViewModel> lstDanhGia = danhGiaDao.layDanhSachView();

        return new ResponseEntity<List<DanhGiaViewModel>>(lstDanhGia , HttpStatus.OK);

    }

    @GetMapping("danhgia/{id}")
    public ResponseEntity<?> getDanhGiaById(@PathVariable ("int") int id){
        if (danhGiaDao.layChiTietTheoMa(id) == null) {
            Messages apiErr = new Messages("Không thể tạo đánh giá có ID: " + id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            DanhGia objDanhGia = danhGiaDao.layChiTietTheoMa(id);
            return new ResponseEntity<DanhGia>(objDanhGia, HttpStatus.OK);
        }
    }

    @PostMapping("danhgia/add")
    public ResponseEntity<?> addDanhGia(@RequestBody DanhGia objDG, HttpSession session){

        objDG.setNguoiDg(Integer.parseInt(session.getAttribute("userId")+""));

        DanhGia objDanhGiaOld = danhGiaDao.getAllByNguoiDungAndPhimId(objDG.getNguoiDg(),objDG.getPhim());

        if(objDanhGiaOld == null) {

            boolean ketQua = danhGiaDao.themMoi(objDG);

            if (ketQua) {
                return new ResponseEntity<DanhGia>(objDG, HttpStatus.OK);
            }

            Messages apiErr = new Messages("Không thể thêm đánh giá");
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {
            objDanhGiaOld.setDiem(objDG.getDiem());
            objDanhGiaOld.setPhim(objDG.getPhim());
            danhGiaDao.capNhat(objDanhGiaOld);
            return new ResponseEntity<DanhGia>(objDanhGiaOld, HttpStatus.OK);
    }
    }


    @PutMapping("danhgia/{id}")
    public ResponseEntity<?> updateDanhGia(@PathVariable("id") int id, @RequestBody DanhGia objDanhGia){
        DanhGia objDanhGiaOld = danhGiaDao.layChiTietTheoMa(id);
        if (objDanhGiaOld == null) {
            Messages apiErr = new Messages("Không tìm thấy đánh giá có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objDanhGiaOld.setDiem(objDanhGia.getDiem());
            objDanhGiaOld.setPhim(objDanhGia.getPhim());
            danhGiaDao.capNhat(objDanhGiaOld);
            return new ResponseEntity<DanhGia>(objDanhGiaOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("danhgia/{id}")
    public ResponseEntity<?> deleteDanhGia(@PathVariable("id") int id){
        DanhGia objDanhGia = danhGiaDao.layChiTietTheoMa(id);
        if (objDanhGia == null) {
            Messages apiErr = new Messages("Không tìm thấy đánh giá có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            danhGiaDao.xoa(id);
            Messages apiErr = new Messages("Xoá đánh giá có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
