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
C1: Day la cach de nhat de trien khai Singleton nhung no co 1 nhuoc diem la
    mac du instance da duoc khoi tao nhung co the se khong dung toi.
    Tuc la luon khoi tao instance ke ca khi khong dung toi no,
    dieu nay gay lang phi ta nguyen.
    ==> Su dung C2 de khac phuc nhuoc diem nay.
*/

public class EagerInitialization {
    
    private static final EagerInitialization instance = new EagerInitialization();
    
    private EagerInitialization(){
        
    }
    
    public static EagerInitialization getInstance(){
        return instance;
    }
}
