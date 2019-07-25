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
public class Employees extends Employee {

    public Employees(String maNV, String tenNV, int namSinh, double luongCB) {
        super(maNV, tenNV, namSinh, luongCB);
    }

    @Override
    public double tinhLuong() {
        return this.getLuongCB();
    }

    public Employees() {
        super();
    }

}
