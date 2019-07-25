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
    4. PepperDecorator va CheeseDecorator cai dat cac phuong thuc mo rong,
        trong truong hop nay, PepperDecorator se them ho tieu vao 1 pizza da co.
        Tinh nang mo rong nay duoc cai dat trong phuong thuc adPepper().
        Tuong tu doi voi CheeseDecorator.
*/
public class CheeseDecorator extends PizzaDecorator{
    
    public CheeseDecorator(IPizza iPizza) {
        super(iPizza);
    }

    private String addCheese(){
        return " + Cheese";
    }
    
    public String doPizza(){
        return super.iPizza.doPizza() + addCheese();
    }
}
