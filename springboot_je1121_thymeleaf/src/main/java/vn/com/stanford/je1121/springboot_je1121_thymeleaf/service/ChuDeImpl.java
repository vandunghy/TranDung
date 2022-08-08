package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository.ChuDeRepository;

import java.util.List;

@Service("chuDeDao")
public class ChuDeImpl implements ChuDeDao {

    @Autowired
    ChuDeRepository chuDeRepository;

    @Override
    public List<ChuDe> layDanhSach() {
        return (List<ChuDe>)chuDeRepository.findAll();
    }

    @Override
    public ChuDe layChiTietTheoMa(Object id) {
        return (ChuDe)chuDeRepository.findById("" + id).get();
    }

    @Override
    public boolean themMoi(ChuDe obj) {

        ChuDe objChuDe = chuDeRepository.save(obj);
        if(objChuDe != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(ChuDe obj) {

        ChuDe objChuDe = chuDeRepository.findById(obj.getMaChuDe()).get();

        if(objChuDe != null)
        {
            objChuDe.setTenChuDe(obj.getTenChuDe());

            chuDeRepository.save(objChuDe);

            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {

        ChuDe objChuDe = layChiTietTheoMa(id);

        if(objChuDe != null)
        {
            chuDeRepository.delete(objChuDe);

            return true;
        }

        return false;
    }

    @Override
    public List<ChuDe> timKiemChuDe(String tuKhoa) {
        if (tuKhoa != null ) {
            return chuDeRepository.timKiemChuDe(tuKhoa);
        }
        return (List<ChuDe>) chuDeRepository.findAll();

    }
}
