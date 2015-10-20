package com.aitiny.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;

public class IValidateServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IValidateService validateService=(IValidateService) ctx.getBean("validateService");
	@Test
	public void testUserSendCode() {
		User user=new User("2500581267@qq.com", "fdsaf", "afds");
		user.setId(1);
		try {
			this.validateService.userSendCode(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUserSendUrl() {
		User user=new User("2500581267@qq.com", "fdsaf", "afds");
		user.setId(1);
		try {
			System.out.println(this.validateService.userSendUrl(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAdminSendCode() {
		Admin admin=new Admin("fsdaf@qq.com", "fsadf", "fdsaf", "ew");
		admin.setId(1);
		try {
			this.validateService.adminSendCode(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveDate() {
		try {
			System.out.println(this.validateService.remove(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveInt() {
		try {
			System.out.println(this.validateService.remove(9));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
