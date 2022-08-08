package vn.com.stanford.je1121.springboot_je1121_thymeleaf.Api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Messages;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.NguoiDung;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.PhongBan;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.PhongBanDao;

import java.util.List;

@RestController
@RequestMapping("api")
public class PhongBanApiController {

    @Autowired
    PhongBanDao phongBanDao;

    @GetMapping("phongban/all")
    public ResponseEntity<List<PhongBan>> getAll(){
        List<PhongBan> lstPhongBan = phongBanDao.layDanhSach();

        return new ResponseEntity<List<PhongBan>>(lstPhongBan, HttpStatus.OK);
    }

    @GetMapping("phongban/{ma}")
    public ResponseEntity<?> getPhongBanById(@PathVariable("ma") String ma){
        if (phongBanDao.layChiTietTheoMa(ma) == null) {
            Messages apiErr = new Messages("Không thể tạo phòng ban có mã: " + ma);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            PhongBan objPhongBan = phongBanDao.layChiTietTheoMa(ma);
            return new ResponseEntity<PhongBan>(objPhongBan, HttpStatus.OK);
        }
    }

    @PostMapping("phongban/add")
    public ResponseEntity<?> addUser(@RequestBody PhongBan objPhongBan){
        boolean ketQua = phongBanDao.themMoi(objPhongBan);
        if (ketQua) {
            return new ResponseEntity<PhongBan>(objPhongBan, HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể tạo mới Phòng ban");

        return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
    }

    @PutMapping("phongban/{ma}")
    public ResponseEntity<?> updateUser(@PathVariable("ma") String ma, @RequestBody PhongBan objPhongBan){
        PhongBan objPBOld = phongBanDao.layChiTietTheoMa(ma);
        if (objPBOld == null) {
            Messages apiErr = new Messages("Không tìm thấy Phòng ban có mã: "+ ma);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            objPBOld.setTenPhong(objPhongBan.getTenPhong());
            phongBanDao.capNhat(objPBOld);
            return new ResponseEntity<PhongBan>(objPBOld, HttpStatus.OK);
        }
    }

    @DeleteMapping("phongban/{ma}")
    public ResponseEntity<?> deleteStudent(@PathVariable("ma") String ma){
        PhongBan objPhongBan = phongBanDao.layChiTietTheoMa(ma);
        if (objPhongBan == null) {
            Messages apiErr = new Messages("Không tìm thấy  Phòng ban có mã: "+ ma);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phongBanDao.xoa(ma);
            Messages apiErr = new Messages("Xoá  Phòng ban có mã: "+ ma);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }
}
