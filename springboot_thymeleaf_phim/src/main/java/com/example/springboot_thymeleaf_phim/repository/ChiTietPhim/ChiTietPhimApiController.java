package com.example.springboot_thymeleaf_phim.repository.ChiTietPhim;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("api")
public class ChiTietPhimApiController {
    @Autowired
    PhimChiTietDao phimChiTietDao;



}
