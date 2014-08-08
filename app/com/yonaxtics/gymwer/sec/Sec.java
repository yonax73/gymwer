package com.yonaxtics.gymwer.sec;

import play.Logger;

import com.yonaxtics.gymwer.sec.crypto.Aes;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class Sec extends Aes {
       
	
	
	public static String dec(String cipher){
		
		try {
			
			
			return decrypt(prepareDec(cipher));
			
		} catch (Exception e) {
			
			Logger.error(e.getMessage());
		}
		
		return cipher;
	}
	
	
	
	
	public static String enc(String value){
		
		try {
			
			return encrypt(padString(value));
			
		} catch (Exception e) {
			
			Logger.error(e.getMessage());
		}
		return value;
	}
	
	private  static String prepareDec(String value){
		
		return value.replace('?', '=').replace(' ', '+');
	}
	
	
}
