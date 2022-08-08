package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.NguoiDung;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.repository.NguoiDungRepository;

import java.util.List;

@Service("nguoiDungDao")
public class NguoiDungImpl implements NguoiDungDao{

    @Autowired
    NguoiDungRepository nguoiDungRepository;


    @Override
    public List<NguoiDung> layDanhSach() {
        return (List<NguoiDung>) nguoiDungRepository.findAll();
    }

    @Override
    public NguoiDung layChiTietTheoMa(Object id)
    {
        NguoiDung objNguoiDung = null;
        try {
            objNguoiDung = nguoiDungRepository.findById(Integer.parseInt("" + id)).get();
        }
        catch(Exception ex)
        {
            System.out.println("Không lấy được người dùng. Chi tiết: " + ex.getMessage());
            objNguoiDung = null;
        }

        return  objNguoiDung;
    }

    @Override
    public boolean themMoi(NguoiDung obj) {
        NguoiDung objNguoiDung = nguoiDungRepository.save(obj);

        if(objNguoiDung != null)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean capNhat(NguoiDung obj) {
        NguoiDung objNguoiDung= nguoiDungRepository.findById(obj.getId()).get();

        if(objNguoiDung != null){

            objNguoiDung.setTenDangNhap(obj.getTenDangNhap());
            objNguoiDung.setMatKhau(obj.getMatKhau());
            objNguoiDung.setHoTen(obj.getHoTen());
            objNguoiDung.setDienThoai(obj.getDienThoai());
            objNguoiDung.setEmail(obj.getEmail());
            objNguoiDung.setDiaChi(obj.getDiaChi());
            objNguoiDung.setRoleId(obj.getRoleId());


            nguoiDungRepository.save(objNguoiDung);

            return true;

        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        NguoiDung objNguoiDung = layChiTietTheoMa(id);

        if(objNguoiDung != null)
        {
            nguoiDungRepository .delete(objNguoiDung);

            return true;
        }

        return false;
    }

    @Override
    public List<NguoiDung> timKiemNguoiDung(String tuKhoa) {
        if (tuKhoa != null ) {
            return nguoiDungRepository.timKiemNguoiDung(tuKhoa);
        }
        return (List<NguoiDung>) nguoiDungRepository.findAll();

    }

    @Override
    public int laySoLuongNguoiDung(){
        return nguoiDungRepository.laySoLuong();
    }

    @Override
    public NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap) {

        List<NguoiDung> lstUser = nguoiDungRepository.findByTenDangNhap(tenDangNhap);

        if(lstUser !=null && lstUser.size()>0)
        {
            return lstUser.get(0);
        }
        return null;
    }

    @Override
    public List<NguoiDung> layDanhSachNguoiDungTheoHoTen(String hoTen) {
        return nguoiDungRepository.findByHoTenLike(hoTen);
    }
}
