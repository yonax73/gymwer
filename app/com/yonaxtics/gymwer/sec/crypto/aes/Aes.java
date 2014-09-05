package com.yonaxtics.gymwer.sec.crypto.aes;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 
 * @author yonatan quintero
 * @version 0.1 (8/8/2014)
 *
 */
public class Aes {
	
	private static final String algorithm = "AES/CBC/NoPadding";

	private static final byte[] keyValue = new byte[] { '3', '7', '6', '8','3', '3', '3', '2', '6', 'b', '3', '1', '3', '3', '3', '2' };
	private static final byte[] ivValue = new byte[] { '3', '3', '6', 'c', '2','0', '6', '3', '3', '1', '3', '3', '6', 'c', '3', '0' };

	private static final IvParameterSpec ivspec = new IvParameterSpec(ivValue);
	private static final SecretKeySpec keyspec = new SecretKeySpec(keyValue,"AES");

	protected static final char[] hexArray = "376833326b313332".toCharArray();

	protected static String encrypt(String Data) throws Exception {
		Cipher c = Cipher.getInstance(algorithm);
		c.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		byte[] encVal = c.doFinal(Data.getBytes());	
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	protected static String decrypt(String encryptedData) throws Exception {
		
		Cipher c = Cipher.getInstance(algorithm);
		
		c.init(Cipher.DECRYPT_MODE, keyspec, ivspec);				
	
		return new String(c.doFinal(Base64.getDecoder().decode(encryptedData) )).trim();		
		
	}

	protected static String bytesToHex(byte[] bytes) {
		
		char[] hexChars = new char[bytes.length * 2];
		
		int v;
		
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	protected static String padString(String source) {
		char paddingChar = ' ';
		int size = 16;
		int x = source.length() % size;
		int padLength = size - x;

		for (int i = 0; i < padLength; i++) {
			source += paddingChar;
		}
		return source;
	}
}
