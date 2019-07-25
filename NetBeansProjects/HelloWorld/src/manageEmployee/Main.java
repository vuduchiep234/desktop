/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manageEmployee;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vuduchiep
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int n;
        Scanner sc = new Scanner(System.in);
        Employee employee;

        ArrayList<Employee> listEmployee = new ArrayList<>();

        System.out.println("Nhap so luong nhan vien: ");
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("========================");
            System.out.println("==Menu==");
            System.out.println("1: Manager");
            System.out.println("2: Data Scientist");
            System.out.println("3: Developer");
            System.out.println("4: Employee");
            System.out.println("========================");
            System.out.print("Nhap lua chon (1 or 2 or 3 or 4): ");
            int option = sc.nextInt();
            System.out.println("=== "+(i+1)+" ===");
            switch(option){
                case 1:
                    System.out.println("NHAP THONG TIN MANAGER ");
                    employee = new Manager();
                    employee.nhap();
                    listEmployee.add(employee);
                    break;
                case 2:
                    System.out.println("NHAP THONG TIN DATA SCIENTIST ");
                    employee = new DataScientist();
                    employee.nhap();
                    listEmployee.add(employee);
                    break;
                case 3:
                    System.out.println("NHAP THONG TIN DEVELOPER");
                    employee = new Developer();
                    employee.nhap();
                    listEmployee.add(employee);
                    break;
                case 4:
                    System.out.println("NHAP THONG TIN EMPLOYEE ");
                    employee = new Employees();
                    employee.nhap();
                    listEmployee.add(employee);
                    break;
            }
            
            
        }
        
        System.out.println("=========================");
        System.out.println("XUAT THONG TIN");
        int k = 1;
        for(Employee employee1 : listEmployee){
            System.out.println("=== "+k+" ===");
            employee1.xuat();
            System.out.println("Luong: " + employee1.tinhLuong());
            k++;
        }
    }

}
