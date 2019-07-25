/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageEmployee;

import java.util.Scanner;

/**
 *
 * @author vuduchiep
 */
public abstract class Employee {

    private String maNV, tenNV;
    private int namSinh;
    private double luongCB;

    Scanner sc = new Scanner(System.in);

    public Employee(String maNV, String tenNV, int namSinh, double luongCB) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.namSinh = namSinh;
        this.luongCB = luongCB;
    }

    public void nhap() {
        System.out.println("Nhap ma nhan vien: ");
        maNV = sc.nextLine();
        this.setMaNV(maNV);
        System.out.println("Nhap ten nhan vien: ");
        tenNV = sc.nextLine();
        this.setTenNV(tenNV);
        System.out.println("Nhap nam sinh: ");
        namSinh = sc.nextInt();
        this.setNamSinh(namSinh);
        System.out.println("Nhap luong co ban ($): ");
        luongCB = sc.nextDouble();
        this.setLuongCB(luongCB);
    }
    
    public void xuat(){
        System.out.println("Ma nhan vien: " + this.getMaNV());
        System.out.println("Ten nhan vien: " + this.getTenNV());
        System.out.println("Nam sinh: " + this.getNamSinh());
    }

    public Employee() {
    }

    public abstract double tinhLuong();

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

}
