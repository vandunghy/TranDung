package com.example.springboot_thymeleaf_phim.controller;

import com.example.springboot_thymeleaf_phim.entity.Messages;
import com.example.springboot_thymeleaf_phim.entity.PhanQuyen;
import com.example.springboot_thymeleaf_phim.service.PhanQuyenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PhanQuyenApiController {
    @Autowired
    PhanQuyenDao phanQuyenDao;


    @GetMapping("phanquyen/all")
    public ResponseEntity<List<PhanQuyen>> getAll(){

        List<PhanQuyen>lstPhanQuyen = phanQuyenDao.layDanhSach();
        return new ResponseEntity<List<PhanQuyen>>(lstPhanQuyen, HttpStatus.OK);
    }

    @GetMapping("phanquyen/{id}")
    public ResponseEntity<?> getPhanQuyenById(@PathVariable("id") int id){

        if(phanQuyenDao.layChiTietTheoMa(id) == null){
            Messages apiErr = new Messages("Không thể lấy chi tiết phân quyền có id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            PhanQuyen objPhanQuyen = phanQuyenDao.layChiTietTheoMa(id);

            return new ResponseEntity<PhanQuyen>(objPhanQuyen,HttpStatus.OK);
        }
    }

    @PostMapping("phanquyen/add")
    public ResponseEntity<?> phanQuyenAdd(@RequestBody PhanQuyen objPhanQuyen){

        boolean ketQua = phanQuyenDao.themMoi(objPhanQuyen);

        if (ketQua){
            return new ResponseEntity<PhanQuyen>(objPhanQuyen,HttpStatus.OK);
        }
        Messages apiErr = new Messages("Không thể thêm mới phân quyền :");
        return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
    }

    @PutMapping("phanquyen/{id}")
    public ResponseEntity<?> updatePhanQuyen(@PathVariable ("id") int id,@RequestBody PhanQuyen objPQ){

        PhanQuyen objPQOld = phanQuyenDao.layChiTietTheoMa(id);
        if(objPQOld == null){
            Messages apiErr = new Messages("Không tìm thấy phân quyền id:"+id);
            return new ResponseEntity<Messages>(apiErr,HttpStatus.NOT_FOUND);
        }else {
            objPQOld.setChucNang(objPQ.getChucNang());
            objPQOld.setVaiTro(objPQ.getVaiTro());
            objPQOld.setXem(objPQ.getXem());
            objPQOld.setThem(objPQ.getThem());
            objPQOld.setSua(objPQ.getSua());
            objPQOld.setXoa(objPQ.getXoa());
            objPQOld.setBaoCao(objPQ.getBaoCao());
            phanQuyenDao.capNhat(objPQOld);
            return new ResponseEntity<PhanQuyen>(objPQOld,HttpStatus.OK);
        }
    }

    @DeleteMapping("phanquyen/{id}")
    public ResponseEntity<?> deletePhanQuyen(@PathVariable("id") int id){

        if (phanQuyenDao.layChiTietTheoMa(id) == null) {
            Messages apiErr = new Messages("Không tìm thấy phân quyền có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phanQuyenDao.xoa(id);
            Messages apiErr = new Messages("Xoá phân quyền có id: "+ id);
            return new ResponseEntity<Messages>(apiErr, HttpStatus.OK);
        }
    }

    @DeleteMapping("phanquyen/{vaiTroId}/{chucNangId}")
    public ResponseEntity<?> xoaDanhMuc(@PathVariable("vaiTroId") int vaiTroId, @PathVariable("chucNangId") int chucNangId) {
        PhanQuyen objPhanQuyen = phanQuyenDao.layChiTietTheoVaiTro_ChucNang(vaiTroId, chucNangId);
        if (objPhanQuyen == null) {
            Messages apiErr = new Messages("Không tìm thấy quyền có vai trò id " + vaiTroId + "và chức năng id" + chucNangId);
            return new ResponseEntity<>(apiErr, HttpStatus.NOT_FOUND);
        } else {
            phanQuyenDao.xoaTheoVaiTro_ChucNang(vaiTroId, chucNangId);
            Messages apiErr = new Messages("Xóa quyền có vai trò id " + vaiTroId + "và chức năng id" + chucNangId);
            return new ResponseEntity<>(apiErr, HttpStatus.OK);
        }
    }
}
