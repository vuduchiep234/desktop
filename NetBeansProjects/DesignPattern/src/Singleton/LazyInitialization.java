/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

/**
 *
 * @author vuduchiep
 */

/*
C2: da khac phuc duoc nhuoc diem cua C1, chi khi nao getInstance duoc goi
    thi instance moi duoc khoi tao.
    Tuy nhien cach nay chi su dung tot trong truong hop don luong,
    truong hop neu co 2 luong cung chay va cung goi ham getInstance 
    tai cung 1 thoi diem thi duong nhien chng ta se co it nhat 2 the hien 
    cua instane.
    ==> De khac phuc bai toan ve da luong nay, su dung C3
*/

public class LazyInitialization {
    private static LazyInitialization instance;
    
    private LazyInitialization(){
        
    }
    
    public static LazyInitialization getInstance(){
        if(instance == null){
            instance = new LazyInitialization();
        }
        return instance;
    }
}
