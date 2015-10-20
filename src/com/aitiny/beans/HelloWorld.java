package com.aitiny.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

public class HelloWorld {
     private String name;
     public void SayHello(){
    	 System.out.println("Hello, "+this.name+"!");
     }
	public String getName() {
		return name;
	}
	@Autowired
	@Qualifier("name")
	public void setName(String name) {
		this.name = name;
	}
     
}
