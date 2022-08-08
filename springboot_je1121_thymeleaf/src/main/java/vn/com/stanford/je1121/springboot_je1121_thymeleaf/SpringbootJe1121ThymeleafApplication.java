package vn.com.stanford.je1121.springboot_je1121_thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.ChuDeDao;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.service.SachDao;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringbootJe1121ThymeleafApplication /*implements CommandLineRunner*/ {

   /* @Autowired
    ChuDeDao chuDeDao;

    @Autowired
    SachDao sachDao;     */


    public static void main(String[] args) {
        SpringApplication.run(SpringbootJe1121ThymeleafApplication.class, args);
    }
    /*
    @Override
    public void run(String... args) throws Exception {

        
        //Thêm mới chủ đề
        ChuDe objChuDe = new ChuDe();
        objChuDe.setMaChuDe("SPT");
        objChuDe.setTenChuDe("Spring Boot, JPA");

        chuDeDao.themMoi(objChuDe);

        //Lay danh sach chu de
        List<ChuDe> lstChuDe = chuDeDao.layDanhSach();

        System.out.println("Danh sách chủ đề:");
        lstChuDe.forEach(cd->{
            System.out.println(cd.getMaChuDe() + "\t" + cd.getTenChuDe());
        });

        Sach objSach = new Sach();

        objSach.setMaSach("CGCN");
        objSach.setTenSach("Cha giàu cha nghèo");

        objSach.setTacGia("Robert Kiyosaki");

        objSach.setGiaSach(200000);
        objSach.setMoTa("Sách nói về kỹ năng quản lý tài chính của 2 người cha của tác giả");
        objSach.setAnhSach("chagiauchangheo.jpg");
        objSach.setMaChuDe("KNS");
        objSach.setNgayTao(new Date());
        objSach.setNgayCapNhat(new Date());
        objSach.setDaDuyet(0);
        sachDao.themMoi(objSach);
              *
        //Lay danh sach sách
        List<Sach> lstSach = sachDao.layDanhSach();

        System.out.println("Danh sách sách:");
        lstSach.forEach(s->{
            System.out.println(s.getMaSach() + "\t" + s.getTenSach());
        });
    }   */
}
