package vn.com.stanford.je1121.springboot_je1121_thymeleaf.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authorizationinterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        //Kiểm tra nếu chưa đăng nhập thì yêu cầu phải xác thực đăng nhập trước
        if(session != null && session.getAttribute("userOnline") != null)
        {
            System.out.println("Bạn đang đăng nhập với tài khoản: " + session.getAttribute("userOnline"));
        }
        else
        {
            response.sendRedirect("../dang-nhap");
        }
        return super.preHandle(request, response, handler);
    }

}
