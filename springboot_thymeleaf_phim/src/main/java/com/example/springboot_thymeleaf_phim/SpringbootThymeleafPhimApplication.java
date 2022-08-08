package com.example.springboot_thymeleaf_phim;

import com.example.springboot_thymeleaf_phim.entity.NguoiDung;
import com.example.springboot_thymeleaf_phim.service.NguoiDungDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringbootThymeleafPhimApplication /*implements CommandLineRunner*/ {


    @Autowired
    NguoiDungDao nguoiDungDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootThymeleafPhimApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        //Lay danh sach chu de
//        List<NguoiDung> lstNguoiDung = nguoiDungDao.getUsersAll();
//
//        System.out.println("Danh sách chủ đề:");
//        lstNguoiDung.forEach(nd -> {
//            System.out.println(nd.getTenDangNhap() + "\t" + nd.getMatKhau());
//        });
//    }
    }

