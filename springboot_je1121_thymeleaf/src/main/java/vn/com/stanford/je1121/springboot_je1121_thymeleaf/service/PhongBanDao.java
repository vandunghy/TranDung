package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.PhongBan;

import java.util.List;

public interface PhongBanDao extends IHanhDong<PhongBan>{
    List<PhongBan> timKiemChuDe(String tuKhoa);
}
