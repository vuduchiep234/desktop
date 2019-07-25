/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author ASUS
 */
public class ConvertUtil {

    public static String convertVND(String number) {
        String pattern = "###,###.###";
        Locale locale = new Locale("de", "DE");
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        return dcf.format(Long.valueOf(number));
    }
}
