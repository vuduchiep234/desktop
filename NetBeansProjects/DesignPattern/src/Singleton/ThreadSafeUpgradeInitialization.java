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
C4: Thuong su dung cach nay cho TH da luong.
    Doi voi TH don luong thi nen su dung C2.
*/

public class ThreadSafeUpgradeInitialization {
    
    private static ThreadSafeUpgradeInitialization instance;
    
    private ThreadSafeUpgradeInitialization(){
        
    }
    
    public static ThreadSafeUpgradeInitialization getInstance(){
        if(instance == null){
            synchronized(ThreadSafeUpgradeInitialization.class){
                if(instance == null){
                    instance = new ThreadSafeUpgradeInitialization();
                }
            }
        }
        return instance;
    }
}
