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
C3: Cach don gian nhat la goi phuong thuc synchronized cua ham getInstance()
    va nhu vay he thong dam bao rang tai cung 1 thoi diem chi co 1 luong co the 
    truy cap vao ham getInstance(), va dam bao rang chi co duy nhat 1 the hien cua class.
    Tuy nhien 1 method synchronized se chay rat cham va ton hieu nang,
    vi vay hay cai tien no 1 chut ==> xem C4
*/

public class ThreadSafeInitialization {
    
    private static ThreadSafeInitialization instance;
    
    private ThreadSafeInitialization(){
        
    }
    
    public static synchronized ThreadSafeInitialization getInstance(){
        if(instance == null){
            instance = new ThreadSafeInitialization();
        }
        return instance;
    }
}
