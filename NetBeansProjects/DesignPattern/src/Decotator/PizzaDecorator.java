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
    3. Tao 1 abstract class PizzaDecorator (trai tim cua so do thiet ke Decorator)
        No giu 1 the hien da ton tai cua pizza.
        Thuoc tinh nay se duoc cai dat thong qua phuong thuc khoi tao, 
        va no duoc mo rong trong khi chay chuong trinh.
*/
public abstract class PizzaDecorator implements IPizza{
    
    protected IPizza iPizza;

    public PizzaDecorator(IPizza iPizza) {
        this.iPizza = iPizza;
    }

    public IPizza getiPizza() {
        return iPizza;
    }

    public void setiPizza(IPizza iPizza) {
        this.iPizza = iPizza;
    }
    
    
}
