/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryMethod;

/**
 *
 * @author vuduchiep
 */

/*
    2. Khai bao cac class de chi dinh dien thoai cua hang nao
        SamSung, Apple, Nokia,...
*/
public class SamSung implements Phone{

    @Override
    public void showInfo() {
        System.out.println("This is SamgSung phone.");
    }
    
}
