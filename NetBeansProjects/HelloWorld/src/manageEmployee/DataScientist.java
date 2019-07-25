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
public class DataScientist extends Employee {

    private int soDA;

    public DataScientist(int soDA, String maNV, String tenNV, int namSinh, double luongCB) {
        super(maNV, tenNV, namSinh, luongCB);
        this.soDA = soDA;
    }

    public void nhap() {
        super.nhap();
        System.out.println("Nhap so du an tham gia trong thang: ");
        soDA = sc.nextInt();
    }

    @Override
    public double tinhLuong() {

        return this.getLuongCB() + this.getLuongCB() * 0.2 + this.soDA * 1500;
    }

    public DataScientist() {
        super();
    }

    public int getSoDA() {
        return soDA;
    }

    public void setSoDA(int soDA) {
        this.soDA = soDA;
    }

}
