/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageEmployee;

/**
 *
 * @author vuduchiep
 */
public class Developer extends Employee {

    private int soDuAn;

    public int getSoDuAn() {
        return soDuAn;
    }

    public void setSoDuAn(int soDuAn) {
        this.soDuAn = soDuAn;
    }

    public Developer(int soDuAn, String maNV, String tenNV, int namSinh, double luongCB) {
        super(maNV, tenNV, namSinh, luongCB);
        this.soDuAn = soDuAn;
    }

    public void nhap() {
        super.nhap();
        System.out.println("Nhap so du an thuc hien trong thang: ");
        soDuAn = sc.nextInt();
    }

    @Override
    public double tinhLuong() {
        return this.getLuongCB() + soDuAn*100;
    }

    public Developer() {
        super();
    }

}
