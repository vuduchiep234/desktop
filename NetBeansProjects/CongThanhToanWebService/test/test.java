
import ctt.utils.CommonUltil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anbq
 */
public class test {
	public static void main(String[] args) throws Exception {
             String input ="vnpay38.98.H29-210519-0003300000020190530094744Thanh to?n cho h? s? c? s? bi?n nh?n s? : 38.98.H29-210519-00031314752100";
             String result = CommonUltil.convertSha256(input);
             System.out.println("result: "+result );
            
	}
}
