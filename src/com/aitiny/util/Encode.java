package com.aitiny.util;

import java.security.MessageDigest;

/**
 * 提供MD5加密的方式
 * @author koala
 *
 */
public class Encode {
	public static String MD5="MD5";
	public static String SHA="SHA";
	/**
	 * 静态加密方法
	 * @param codeType 加密方式（MD5，SHA）
	 * @param content 加密内容
	 * @return 
	 */
   public static String getEncode(String codeType,String content){
	   try {
		MessageDigest digest=MessageDigest.getInstance(codeType);
		digest.reset();//清空一下
		digest.update(content.getBytes());
		StringBuilder sb=new StringBuilder();
		for(byte b : digest.digest()){
			sb.append(Integer.toHexString((b>>4) & 0xf));
			sb.append(Integer.toHexString(b & 0xf));
		}
		return sb.toString();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return null;
   }
//   public static void main(String [] args){
//	   String str=Encode.getEncode("MD5", "Hello world!");
//	   System.out.println(str);
//	   String str2=Encode.getEncode("MD5", "Hello world!");
//	   System.out.println(str2);
//	   String str3=Encode.getEncode("SHA", "Hello world!");
//	   System.out.println(str3);
// 	}
}
