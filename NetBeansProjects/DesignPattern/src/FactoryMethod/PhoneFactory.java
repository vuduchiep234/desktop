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
    3. Sau khi co cac class *Phone va enum class PhoneType
        chung ta se tao FactoryClass co ten la PhoneFactory.
        Ben trong class co chua FactoryMethod getPhone la phuong thuc
        dung de khoi tao 1 dien thoai tuy y'.
 */
public class PhoneFactory {

    public Phone getPhone(PhoneType phoneType) {

        Phone phoneCreated = null;
        switch (phoneType) {
            case SAMSUNG:
                phoneCreated = new SamSung();
                break;

            case APPLE:
                phoneCreated = new Apple();
                break;

            case NOKIA:
                phoneCreated = new Nokia();
                break;

        }
        return phoneCreated;
    }
}
