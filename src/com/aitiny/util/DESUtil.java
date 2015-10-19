package com.aitiny.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtil {
	//指定DES加密解密所用的密钥
	private static Key key;
	private static String KEY_STR="my123Key";
	
	static{
		try {
			KeyGenerator generator=KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key=generator.generateKey();
			generator=null;
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	//对字符串进行DES加密，返回BASE64编码的加密字符串
	public static String getEncryptString(String str){
		BASE64Encoder base64en=new BASE64Encoder();
		try {
			byte[] strBytes=str.getBytes("UTF8");
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes=cipher.doFinal(strBytes);
			return base64en.encode(encryptStrBytes);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//对BASE64编码的加密字符串进行解密，返回解密后的字符串
	public static String getDecrytString (String str){
		BASE64Decoder base64De=new BASE64Decoder();
		try {
			byte[] strBytes=base64De.decodeBuffer(str);
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes=cipher.doFinal(strBytes);
			return new String(decryptStrBytes,"UTF8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static void main(String[] args) {
		String str=	DESUtil.getEncryptString("LYDAI147258369");
		System.out.println(str);
		System.out.println(DESUtil.getEncryptString("root"));
		//System.out.println(DESUtil.getDecrytString("WQOPPaEFNcs="));
		
	}
	
}