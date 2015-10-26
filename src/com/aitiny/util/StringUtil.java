package com.aitiny.util;

import java.util.UUID;
/**
 * <li>字符串首字母转换大写
 * <li>获取UUID
 * @author koala
 *
 */
public class StringUtil {
		public static String initcap(String str){
			if(str==null && "".equals(str)){
				return null;
			}
			return str.substring(0, 1).toUpperCase()+str.substring(1);
		}
		
		/** 
	     * 获得一个UUID 
	     * @return String UUID 
	     */ 
	    public static String getUUID(){ 
	        String s = UUID.randomUUID().toString(); 
	        //去掉“-”符号 
	        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	    } 
	    /** 
	     * 获得指定数目的UUID 
	     * @param number int 需要获得的UUID数量 
	     * @return String[] UUID数组 
	     */ 
	    public static String[] getUUID(int number){ 
	        if(number < 1){ 
	            return null; 
	        } 
	        String[] ss = new String[number]; 
	        for(int i=0;i<number;i++){ 
	            ss[i] = getUUID(); 
	        } 
	        return ss; 
	    } 
//	    public static void main(String[] args){ 
//	        String[] ss = getUUID(10); 
//	        for(int i=0;i<ss.length;i++){ 
//	            System.out.println(ss[i]); 
//	        } 
//	    } 
	    
	    public static String htmlReplaceTag(String content) {
	    	 if(content==null) return "";        
	    	     String html = content;
	    	     
	    	 //    html = html.replace( "'", "&apos;");
	    	     html = html.replaceAll( "&", "&amp;");
	    	     html = html.replace( "\"", "&quot;");  //"
	    	     html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
	    	     html = html.replace( " ", "&nbsp;");// 替换空格
	    	     html = html.replace("<", "&lt;");
	    	 
	    	     html = html.replaceAll( ">", "&gt;");
	    	   
	    	     return html;
	    	 }
	    public static String htmlReplaceTag2(String input) {
	    	 if (!hasSpecialChars(input)) {   
	             return input;   
	         }   
	         StringBuffer filtered = new StringBuffer(input.length());   
	         char c;   
	         for (int i = 0; i <= input.length() - 1; i++) {   
	             c = input.charAt(i);   
	             switch (c) {   
	             case '<':   
	                 filtered.append("&lt;");   
	                 break;   
	             case '>':   
	                 filtered.append("&gt;");   
	                 break;   
	             case '"':   
	                 filtered.append("&quot;");   
	                 break;   
	             case '&':   
	                 filtered.append("&amp;");   
	                 break;   
	             default:   
	                 filtered.append(c);   
	             }   
	   
	         }   
	         return (filtered.toString());   
	    }
	    public static boolean hasSpecialChars(String input) {   
	        boolean flag = false;   
	        if ((input != null) && (input.length() > 0)) {   
	            char c;   
	            for (int i = 0; i <= input.length() - 1; i++) {   
	                c = input.charAt(i);   
	                switch (c) {   
	                case '>':   
	                    flag = true;   
	                    break;   
	                case '<':   
	                    flag = true;   
	                    break;   
	                case '"':   
	                    flag = true;   
	                    break;   
	                case '&':   
	                    flag = true;   
	                    break;   
	                }   
	            }   
	        }   
	        return flag;   
	    }   
}
