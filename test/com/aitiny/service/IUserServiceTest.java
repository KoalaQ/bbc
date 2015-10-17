package com.aitiny.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Collections;
import com.aitiny.dao.vo.Fans;
import com.aitiny.dao.vo.User;

public class IUserServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IUserService userService=(IUserService) ctx.getBean("userService");
	@Test
	public void testLogin() {
		User user=new User();
		user.setId(1);
		user.setEmail("123@qq.com");
		user.setPassword("123");
		user.setUuid("fdsafas");
		try {
			System.out.println(this.userService.login(user));
			System.out.println(this.userService.getUser(user.getEmail()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUser() {
		try {
			System.out.println(this.userService.getUser("tet11"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLogout() {
		User user=new User();
		user.setId(16);
		try {
			System.out.println(this.userService.logout(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testHasEmail() {
		try {
			System.out.println(this.userService.hasEmail("test11"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testHaiNickName() {
		try {
			System.out.println(this.userService.hasNickName("test11"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRegister() {
		User user=new User("123@qq.com", "123", "1fdsa");
		try {
			System.out.println(this.userService.register(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangePassword() {
		User user=new User("fadfsf@qq.com", "fasdf", "1f1fdsa");
		user.setId(1);
		user.setPassword("fdasfsa");
		try {
			System.out.println(this.userService.changePassword(user, "1234"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateInfo() {
		User user=new User("kit", "fsaf", 1);
		user.setId(1);
		try {
			System.out.println(this.userService.updateInfo(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddVantages() {
		try {
			System.out.println(this.userService.addVantages(1, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testlistUser(){
		try {
			System.out.println(this.userService.listUser("id", "", 1, 10, "id", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testaddFans(){
		try {
			System.out.println(this.userService.addFans(new Fans(12, 122, 1, new Date())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testdeleteFans(){
		try {
			System.out.println(this.userService.deleteFans(new Fans(1, 12, 1, new Date())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testlistFans(){
		try {
			System.out.println(this.userService.listFans("id", "", 2, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testaddCollect(){
		try {
			System.out.println(this.userService.addCollect(new Collections(132, 123, new Date(), "FDSA", "test中文")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testdeleteCollect(){
		try {
			System.out.println(this.userService.deleteCollect(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testlistCollections(){
		try {
			System.out.println(this.userService.listCollections("id", "", 1, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
