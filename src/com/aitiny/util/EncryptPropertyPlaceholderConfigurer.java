package com.aitiny.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] encryptPropNames={"username","password"};

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		if(isEncryptProp(propertyName)){
			String decryptValue=DESUtil.getDecrytString(propertyValue);
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
