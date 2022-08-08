package vn.com.stanford.je1121.springboot_je1121_thymeleaf.Api;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("call-api")
public class PhongBanCallApIController {

    @RequestMapping(value ="/phongban")
    public String hienThiDanhSachPhongBan(){

        return "QuanLyPhongBanCallApi";
    }
}
