package com.aitiny.util;

import java.io.IOException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] encryptPropNames={"jdbc.username","jdbc.password"};

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		if(isEncryptProp(propertyName)){
			//System.out.println("属性编辑"+propertyName);
			String decryptValue = null;
			try {
				decryptValue = DesUtil2.decrypt(propertyValue, "LYD147258");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(decryptValue);
			return decryptValue;
			}
		return propertyValue;
	}
	private boolean isEncryptProp(String propertyName){
		for(String encryptPropName : encryptPropNames){
			if(encryptPropName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
	
}
