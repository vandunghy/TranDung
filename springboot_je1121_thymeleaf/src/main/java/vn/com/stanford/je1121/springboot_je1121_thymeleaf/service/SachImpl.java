package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository.SachRepository;

import java.util.List;

@Service("sachDao")
public class SachImpl implements SachDao {

    @Autowired
    SachRepository sachRepository;

    @Override
    public List<Sach> layDanhSach() {
        return (List<Sach>) sachRepository.findAll();
    }

    @Override
    public Sach layChiTietTheoMa(Object id) {
        Sach objSach = null;
        try {
            objSach = sachRepository.findById("" + id).get();
        }
        catch(Exception ex)
        {
            System.out.println("Không lấy được sách. Chi tiết: " + ex.getMessage());
            objSach = null;
        }

        return  objSach;
    }

    @Override
    public boolean themMoi(Sach obj) {
        Sach objSach = sachRepository.save(obj);
        if(objSach != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(Sach obj) {
        Sach objSach = sachRepository.findById(obj.getMaSach()).get();

        if(objSach != null)
        {
            objSach.setTenSach(obj.getTenSach());
            objSach.setMoTa(obj.getMoTa());
            objSach.setAnhSach(obj.getAnhSach());
            objSach.setGiaSach(obj.getGiaSach());
            objSach.setTacGia(obj.getTacGia());
            objSach.setMaChuDe(obj.getMaChuDe());
            objSach.setNgayCapNhat(obj.getNgayCapNhat());
           
            sachRepository.save(objSach);

            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        Sach objSach = layChiTietTheoMa(id);

        if(objSach != null)
        {
            sachRepository.delete(objSach);

            return true;
        }

        return false;
    }

    @Override
    public List<Sach> timKiemSach(String tuKhoa, String maChuDe) {
        if (tuKhoa != null || maChuDe != null) {
            return sachRepository.timKiemSach(tuKhoa, maChuDe);
        }
        return (List<Sach>) sachRepository.findAll();

    }
}
