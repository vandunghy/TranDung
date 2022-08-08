package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;

import java.util.List;

public interface SachDao extends IHanhDong<Sach> {
    List<Sach> timKiemSach(String tuKhoa, String maChuDe);
}
