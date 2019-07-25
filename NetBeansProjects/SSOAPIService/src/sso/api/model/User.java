/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.model;

import java.util.Date;

/**
 *
 * @author lannt54
 */
public class User {

    
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String hoVaTen;
    private String gioiTinh;
    private String ngaySinh;
    private String danToc;
    private String quocTich;
    private int loaiGiayTo;
    private String soGiayTo;
    private String ngayCapGiayTo;
    private String noiCapGiayTo;
    private String hoKhauThuongTru;
    private String email;
    private String soDienThoai;
    private String soNha;
    private String tenHuyen;
    private String tenPhuong;
    private String tenDuong;
    private String diaChiDangKy;
    private int toChucHayCaNhan;
    private int trangThai;

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public void setLoaiGiayTo(int loaiGiayTo) {
        this.loaiGiayTo = loaiGiayTo;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public void setNgayCapGiayTo(String ngayCapGiayTo) {
        this.ngayCapGiayTo = ngayCapGiayTo;
    }

    public void setNoiCapGiayTo(String noiCapGiayTo) {
        this.noiCapGiayTo = noiCapGiayTo;
    }

    public void setHoKhauThuongTru(String hoKhauThuongTru) {
        this.hoKhauThuongTru = hoKhauThuongTru;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public void setTenPhuong(String tenPhuong) {
        this.tenPhuong = tenPhuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }

    public void setDiaChiDangKy(String diaChiDangKy) {
        this.diaChiDangKy = diaChiDangKy;
    }

    public void setToChucHayCaNhan(int toChucHayCaNhan) {
        this.toChucHayCaNhan = toChucHayCaNhan;
    }

    public int getId() {
        return id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }
    
    public String getDanToc() {
        return danToc;
    }
    
    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public int getLoaiGiayTo() {
        return loaiGiayTo;
    }

    public String getSoGiayTo() {
        return soGiayTo;
    }

    public String getNgayCapGiayTo() {
        return ngayCapGiayTo;
    }

    public String getNoiCapGiayTo() {
        return noiCapGiayTo;
    }

    public String getHoKhauThuongTru() {
        return hoKhauThuongTru;
    }

    public String getEmail() {
        return email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getSoNha() {
        return soNha;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public String getTenPhuong() {
        return tenPhuong;
    }

    public String getTenDuong() {
        return tenDuong;
    }

    public String getDiaChiDangKy() {
        return diaChiDangKy;
    }

    public int getToChucHayCaNhan() {
        return toChucHayCaNhan;
    }

    public User() {
    }

    
}
