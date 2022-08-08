package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.PhongBan;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository.PhongBanRepository;

import java.util.List;

@Service("phongBanDao")
public class PhongBanImpl implements PhongBanDao{

    @Autowired
    PhongBanRepository phongBanRepository;

    @Override
    public List<PhongBan> layDanhSach() {
        return (List<PhongBan>)phongBanRepository.findAll();
    }

    @Override
    public PhongBan layChiTietTheoMa(Object ma) {
        PhongBan objPhongBan = null;
        try {
            objPhongBan = phongBanRepository.findById("" + ma).get();
        }
        catch(Exception ex)
        {
            System.out.println("Không lấy được sách. Chi tiết: " + ex.getMessage());
            objPhongBan = null;
        }

        return  objPhongBan;
    }

    @Override
    public boolean themMoi(PhongBan obj) {
        PhongBan objPhonBan = phongBanRepository.save(obj);

        if(objPhonBan !=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(PhongBan obj) {
        PhongBan objPhongBan = phongBanRepository.findById(obj.getMaPhong()).get();

        if(objPhongBan != null){

            objPhongBan.setTenPhong(obj.getTenPhong());

            phongBanRepository.save(objPhongBan);
        }
        return false;
    }

    @Override
    public boolean xoa(Object ma) {

        PhongBan objPhongBan = layChiTietTheoMa(ma);

        if(objPhongBan != null){
            phongBanRepository.delete(objPhongBan);
            return true;

        }
        return false;
    }

    @Override
    public List<PhongBan> timKiemChuDe(String tuKhoa) {
        return null;
    }
}
