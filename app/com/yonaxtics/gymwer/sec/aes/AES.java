package com.yonaxtics.gymwer.sec.aes;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.yonaxtics.gymwer.util.Constant;

// TODO: Implement 256-bit version like: http://securejava.wordpress.com/2012/10/25/aes-256/
public class AES {
	

	
    public AES() {
 
    }
    
    public String encrypt() {
         
    	return null;
    }
    
    public String decrypt(String ciphertext) {
    	
    	String plaintext = null;
    	
        try {
        	
        	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        	KeySpec spec = new PBEKeySpec(Constant.KEY.toCharArray(), "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55".getBytes(), 65536, 256);
        	SecretKey tmp = factory.generateSecret(spec);
        	SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        	/* Encrypt the message. */
  //      	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        	cipher.init(Cipher.ENCRYPT_MODE, secret);
  //         	AlgorithmParameters params = cipher.getParameters();
    //    	byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
//        	byte[] ciphertext = cipher.doFinal("Hello, World!".getBytes("UTF-8"));
        	
        	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         	AlgorithmParameters params = cipher.getParameters();
        	byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        	cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        	 plaintext = new String(cipher.doFinal(ciphertext.getBytes()), "UTF-8");
        	System.out.println(plaintext);
        }
        catch (Exception e) {
           
        }
        
        return plaintext;
    }
    
  
}