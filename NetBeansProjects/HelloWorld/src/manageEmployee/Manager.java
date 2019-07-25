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
public class Manager extends Employee {

    public Manager(String maNV, String tenNV, int namSinh, double luongCB) {
        super(maNV, tenNV, namSinh, luongCB);
    }

    public Manager() {
        super();
    }

    @Override
    public double tinhLuong() {

        return this.getLuongCB() + this.getLuongCB() * 0.25;
    }

}
