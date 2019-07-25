/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decotator;

/**
 *
 * @author vuduchiep
 */

/*
    2. TomatoPizza va ChickenPizza la nhung cai dat, trien khai cua IPizza.
        Chung cung cap cu the cac the hien cua lop ma chung ta can mo rong
        trong qua trinh chuong trinh dang chay.
*/
public class TomatoPizza implements IPizza{

    @Override
    public String doPizza() {

        return "I am Tomato Pizza";
    }
    
}
