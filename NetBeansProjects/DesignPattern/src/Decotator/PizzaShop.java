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
    5. Va cuoi cung chung ta can viet lop de chay cac cai dat tren
        la lop PizzaShop
*/
public class PizzaShop {
    
    public static void main(String[] args) {
        
        IPizza tomato = new TomatoPizza();
        IPizza chicken = new ChickenPizza();
        
        System.out.println(tomato.doPizza());
        System.out.println(chicken.doPizza());
        
        // Su dung Decorator pattern de tao ra cac loai Pizza moi
        //  tu cac Pizza dang ton tai 1 cach linh dong.
        
        // Them Pepper vao Tomato Pizza
        PepperDecorator pepperDecorator = new PepperDecorator(tomato);
        System.out.println(pepperDecorator.doPizza());
        
        // Them Cheese vao Tomato Pizza
        CheeseDecorator cheeseDecorator = new CheeseDecorator(tomato);
        System.out.println(cheeseDecorator.doPizza());
        
        // Them ca Cheese va Pepper vao Tomato Pizza
        CheeseDecorator pizza = new CheeseDecorator(pepperDecorator);
        System.out.println(pizza.doPizza());
    }
}
