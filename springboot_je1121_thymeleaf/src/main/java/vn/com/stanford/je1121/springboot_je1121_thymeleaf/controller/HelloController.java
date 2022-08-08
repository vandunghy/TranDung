package vn.com.stanford.je1121.springboot_je1121_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/gioi-thieu")
    public String gioiThieuSpringBoot(Model model)
    {
        model.addAttribute("message", "Chào mừng bạn đến với Spring Boot + Thymeleaf !");

        return "GioiThieu";
    }
}
