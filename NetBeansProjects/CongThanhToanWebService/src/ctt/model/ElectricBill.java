/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.model;

/**
 *
 * @author ASUS
 */
public class ElectricBill {
    private String maHoaDon;
    private String tenNguoiNop;
    private String soCMND;
    private String diaChi;
    private String soTien;
    private String trangThai;

    public ElectricBill() {
    }

    public ElectricBill(String maHoaDon, String tenNguoiNop, String soCMND, String diaChi, String soTien, String trangThai) {
        this.maHoaDon = maHoaDon;
        this.tenNguoiNop = tenNguoiNop;
        this.soCMND = soCMND;
        this.diaChi = diaChi;
        this.soTien = soTien;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
    
    public String getTenNguoiNop() {
        return tenNguoiNop;
    }

    public void setTenNguoiNop(String tenNguoiNop) {
        this.tenNguoiNop = tenNguoiNop;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}
