package com.aitiny.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Log;

public class ILogServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	ILogService logService=(ILogService) ctx.getBean("logService");
	@Test
	public void testInsert() {
		Log log=new Log(null, 1, "test", new Date(), "user");
		try {
			System.out.println(logService.insert(log));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	@Test
	public void testRemove() {
		try {
			System.out.println(logService.remove(new Date()));;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFind() {
		try {
			System.out.println(this.logService.find(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListAll() {
		try {
			System.out.println(this.logService.listAll("id", "", 1, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListAllOrder() {
		try {
			System.out.println(this.logService.listAllOrder("id", "", 1, 10, "id", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
