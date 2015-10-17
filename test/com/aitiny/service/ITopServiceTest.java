package com.aitiny.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ITopServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	ITopService topService=(ITopService) ctx.getBean("topService"); 
	@Test
	public void testRefreshTop() {
		try {
			System.out.println(this.topService.refreshTop(10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddToTop() {
		try {
			System.out.println(this.topService.addToTop(2, 32, new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveTop() {
		try {
			System.out.println(this.topService.removeTop(32));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
