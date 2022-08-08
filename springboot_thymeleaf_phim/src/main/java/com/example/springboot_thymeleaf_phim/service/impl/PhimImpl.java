package com.example.springboot_thymeleaf_phim.service.impl;

import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import com.example.springboot_thymeleaf_phim.repository.PhimRepository;
import com.example.springboot_thymeleaf_phim.repository.PhimSortRepository;
import com.example.springboot_thymeleaf_phim.service.PhimDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("phimDao")
public class PhimImpl implements PhimDao {


    @Autowired
    PhimRepository phimRepository;

    @Override
    public List<Phim> layDanhSach() {

        return (List<Phim>) phimRepository.findAll() ;
    }

    @Override
    public Phim layChiTietTheoMa(Object id) {
        Phim objPhim = null;

        try{
            objPhim = phimRepository.findById(Integer.parseInt("" +id)).get();
        }catch (Exception ex){
            System.out.println("Không thể lấy chi tiết phim" +ex.getMessage());
            objPhim = null;
        }

        return objPhim;
    }

    @Override
    public boolean themMoi(Phim obj) {
        Phim objPhim = phimRepository.save(obj);

        if(objPhim != null){

            return true;
        }

        return false;
    }

    @Override
    public boolean capNhat(Phim obj) {

        Phim objPhim = phimRepository.findById(obj.getId()).get();

        if(objPhim != null){

            objPhim.setTenPhim(obj.getTenPhim());
            objPhim.setMoTa(obj.getMoTa());
            objPhim.setNamSanXuat(obj.getNamSanXuat());
            objPhim.setThoiLuong(obj.getThoiLuong());
            objPhim.setNgayChieu(obj.getNgayChieu());
            objPhim.setQuocGia(obj.getQuocGia());
            objPhim.setDienVien(obj.getDienVien());
            objPhim.setDaoDien(obj.getDaoDien());
            objPhim.setTheLoai(obj.getTheLoai());
            objPhim.setNgayDuyet(obj.getNgayDuyet());
            objPhim.setTrangThai(obj.getTrangThai());
            objPhim.setNgayTao(obj.getNgayTao());
            objPhim.setNgayCapNhat(obj.getNgayCapNhat());
            objPhim.setAnh(obj.getAnh());
            objPhim.setNguoiDuyet(obj.getNguoiDuyet());
            objPhim.setNoiDung(obj.getNoiDung());
            phimRepository.save(objPhim);
            return true;

        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {

        Phim objPhim = layChiTietTheoMa(id);

        if(objPhim != null){

            phimRepository.delete(objPhim);

            return true;
        }
        return false;
    }


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PhimImpl(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Autowired
    EntityManager entityManager;


    @Override
    public List<PhimViewModel> layDanhSachView() {

        List<PhimViewModel> lstPhim = new ArrayList<>();

        Query query = entityManager
                .createQuery("SELECT p.id,p.tenPhim, p.moTa, p.namSanXuat, p.thoiLuong, p.ngayChieu, qg.tenQuocGia,dv.hoTen,dd.hoTen,tl.tenTheLoai," +
                        "p.ngayDuyet, p.ngayChieu, p.ngayTao, p.ngayCapNhat, nd.hoTen as nguoiDuyet, p.noiDung, p.anh, p.trangThai FROM Phim p "
                        +" INNER JOIN QuocGia qg ON p.quocGia = qg.id"
                        +" INNER JOIN DienVien dv ON p.dienVien = dv.id"
                        +" INNER JOIN DaoDien dd ON p.daoDien = dd.id"
                        +" INNER JOIN NguoiDung nd ON p.nguoiDuyet = nd.id"
                        +" INNER JOIN TheLoai tl ON p.theLoai = tl.id");

        List<Object[]> lstObject = query.getResultList();

        for (Object[] r : lstObject) {
            PhimViewModel objPhim = new PhimViewModel();

            objPhim.setId(Integer.parseInt("" + r[0]));
            objPhim.setTenPhim("" + r[1]);
            objPhim.setMoTa("" + r[2]);
            objPhim.setNamSanXuat((Date) r[3]);
            objPhim.setThoiLuong(Integer.parseInt( "" +r[4]));
            objPhim.setNgayChieu((Date) r[5]);
            objPhim.setTenQuocGia("" +r[6]);
            objPhim.setTenDienVien("" + r[7]);
            objPhim.setTenDaoDien("" + r[8]);
            objPhim.setTenTheLoai("" + r[9]);
            objPhim.setNgayDuyet((Date) r[10]);
            objPhim.setNgayChieu((Date) r[11]);
            objPhim.setNgayTao((Date) r[12]);
            objPhim.setNgayCapNhat((Date) r[13]);
            objPhim.setHoTen("" + r[14]);
            objPhim.setNoiDung("" + r[15]);
            objPhim.setAnh("" + r[16]);
            if (Integer.parseInt(""+r[17]) == 0) {
                objPhim.setTrangThai("Chưa duyệt");
            } else {
                objPhim.setTrangThai("Đã duyệt");
            }
            lstPhim.add(objPhim);
        }
        return lstPhim;
    }

    @Override
    public List<PhimViewModel> timKiemPhim(String tuKhoa,Integer theLoai,Integer quocGia, Integer dienVien, Integer daoDien, Date startDate, Date endDate) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT p.ID, p.Ten_Phim, p.Mo_Ta, p.Nam_San_Xuat,\n");
        builder.append("p.Thoi_Luong, p.Ngay_Chieu, qg.Ten_Quoc_Gia,dv.Ho_Ten,\n");
        builder.append("dd.Ho_Ten,tl.ten_The_Loai, p.Ngay_Duyet, \n");
        builder.append("p.Ngay_Tao, p.Ngay_Cap_Nhat, nd.Ho_Ten ,\n");
        builder.append("p.Noi_Dung, p.Anh, p.Trang_Thai FROM Phim p \n");
        builder.append("INNER JOIN Quoc_Gia qg ON p.Quoc_Gia_ID = qg.id \n");
        builder.append("INNER JOIN Dien_Vien dv ON p.Dien_Vien_ID = dv.id \n");
        builder.append("INNER JOIN Dao_Dien dd ON p.Dao_Dien_ID = dd.id \n");
        builder.append("INNER JOIN Nguoi_Dung nd ON p.Nguoi_Duyet_ID = nd.id \n");
        builder.append("INNER JOIN The_Loai tl ON p.The_Loai_ID = tl.id \n");
        builder.append("WHERE (TEN_PHIM LIKE '%' || :tuKhoa || '%' \n");
        builder.append("OR NAM_SAN_XUAT LIKE '%' || :tuKhoa || '%'\n");
        builder.append("OR Ten_Quoc_Gia LIKE '%' || :tuKhoa || '%')\n");
        if (quocGia != null) {
            builder.append("AND QUOC_GIA_ID = :quocGia \n");
        }
        if (theLoai != null) {
            builder.append("AND THE_LOAI_ID = :theLoai \n");
        }
        if (dienVien != null) {
            builder.append("AND DIEN_VIEN_ID = :dienVien \n");
        }
        if (daoDien != null) {
            builder.append("AND DAO_DIEN_ID = :daoDien \n");
        }
        if (startDate != null && endDate == null) {
            builder.append("AND NAM_SAN_XUAT >= :startDate ");
        }
        if (startDate == null && endDate != null) {
            builder.append("AND NAM_SAN_XUAT <= :endDate ");
        }
        if (startDate != null && endDate != null) {
            builder.append("AND (NAM_SAN_XUAT BETWEEN :startDate AND :endDate) ");
        }
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tuKhoa", tuKhoa);
        params.addValue("quocGia", quocGia);
        params.addValue("theLoai", theLoai);
        params.addValue("dienVien", dienVien);
        params.addValue("daoDien", daoDien);
        params.addValue("startDate", startDate);
        params.addValue("endDate", endDate);
        return jdbcTemplate.query(builder.toString(), params, new BeanPropertyRowMapper<>(PhimViewModel.class));
    }

    @Override
    public List<PhimViewModel> layDanhSachPhim() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT p.ID,p.Anh, p.Ten_Phim,p.Trang_thai, \n");
        builder.append("(SELECT AVG(SUM(Diem)) FROM DANH_GIA where Phim_Id = p.id  GROUP BY DIEM) as Diem  From Phim p  \n");

        return jdbcTemplate.query(builder.toString(), new BeanPropertyRowMapper<>(PhimViewModel.class));
    }

    @Autowired
    PhimSortRepository phimSortRepository;

    @Override
    public Page<Phim> phanTrang(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 8);
        return phimSortRepository.findAll(pageable);
    }




}
