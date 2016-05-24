package com.rede.project.util;

import java.io.FileInputStream;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.jfree.util.Log;


public class Utils {
	
	private Utils(){}

	public static String openFile(String file){
		FileInputStream inputStream = null;
		String text = "";
		try {
			inputStream = new FileInputStream(file);
		    text = IOUtils.toString(inputStream);
		    inputStream.close();
		} catch(Exception e){
		   Log.error(e);
		}	
		return text;
	}
	
	public static String decryptFile(String encryptedString){
		
		byte[] encrypted = encryptedString.getBytes();
		Key key = new SecretKeySpec("teste".getBytes(), "AES");
		byte[] decryptedData = null;
		String result = "";
		Cipher cipher;
		
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedData = cipher.doFinal(encrypted);
			result = Arrays.toString(decryptedData);
		} catch (Exception e) {
			Log.error(e);
		}		
		
		return result;
	}
	
	public static String getDatabaseAccess(){
		String encrypted = openFile("databse.txt");
		encrypted = decryptFile(encrypted);		
		return encrypted;
	}
	
}
