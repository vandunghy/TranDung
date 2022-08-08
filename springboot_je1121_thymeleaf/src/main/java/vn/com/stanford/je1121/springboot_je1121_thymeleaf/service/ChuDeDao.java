package vn.com.stanford.je1121.springboot_je1121_thymeleaf.service;

import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.ChuDe;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.model.Sach;

import java.util.List;

public interface ChuDeDao extends IHanhDong<ChuDe> {
    List<ChuDe> timKiemChuDe(String tuKhoa);
}
