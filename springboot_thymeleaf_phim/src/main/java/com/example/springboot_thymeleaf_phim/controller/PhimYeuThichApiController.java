package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimYeuThich;
import com.example.springboot_thymeleaf_phim.service.PhimYeuThichDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api")
public class PhimYeuThichApiController {


    @Autowired
    PhimYeuThichDao phimYeuThichDao;

    @GetMapping("phimyeuthich/all")
    public ResponseEntity<List<PhimYeuThich>> getAll(Model model){
        List<PhimYeuThich> lstPYT = phimYeuThichDao.layDanhSach();
        return new ResponseEntity<List<PhimYeuThich>> (lstPYT, HttpStatus.OK);
    }
    @GetMapping("phimyeuthich/{id}")
    public ResponseEntity<?> getAll( @PathVariable("id") Integer id){

        if(phimYeuThichDao.layChiTietTheoMa(id) == null){
            Messages apiEr = new Messages("không thể lấy ci tiết có Id la:" +id);
            return new ResponseEntity<Messages>(apiEr,HttpStatus.NOT_FOUND);
        }else {

            PhimYeuThich objPYT = phimYeuThichDao.layChiTietTheoMa(id);
            return new ResponseEntity<PhimYeuThich>(objPYT,HttpStatus.OK);
        }
    }

    @PostMapping("phimyeuthich/add")
    public ResponseEntity<?> addPhimYeuThich(@RequestBody PhimYeuThich objPhim, HttpSession session){

        objPhim.setNguoiDung(Integer.parseInt(session.getAttribute("userId")+""));

            boolean ketQua = phimYeuThichDao.themMoi(objPhim);

            if (ketQua) {
                return new ResponseEntity<PhimYeuThich>(objPhim, HttpStatus.OK);
            }
            Messages apiErr = new Messages("Không thể tạo mới phim");
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);



    }


    @PutMapping("phimyeuthich/{id}")
    public ResponseEntity<?>updatePhimYeuThich(@PathVariable("id")int id,@RequestBody PhimYeuThich objPhim){
        PhimYeuThich objPhimOld = phimYeuThichDao.layChiTietTheoMa(id);
        if(objPhimOld == null){
            Messages apiErr = new Messages("Không thể tìm thấy phim có Id là:" +id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        }else {

            objPhimOld.setPhimId(objPhim.getPhimId());
            objPhimOld.setNguoiDung(objPhim.getNguoiDung());
            phimYeuThichDao.capNhat(objPhimOld);
            return new ResponseEntity<PhimYeuThich>(objPhimOld,HttpStatus.OK);
        }
    }
    @DeleteMapping("phimyeuthich/{id}")
    public ResponseEntity<?> deletePhimYeuThich(@PathVariable("id") int id){
        PhimYeuThich objPhim = phimYeuThichDao.layChiTietTheoMa(id);
        if (objPhim == null) {
            Messages apiErr = new Messages("Không tìm thấy Phim có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phimYeuThichDao.xoa(id);
            Messages apiErr = new Messages("Xoá phim có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }

}
