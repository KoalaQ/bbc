package com.aitiny.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.Board;

public class IAdminServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IAdminService adminService=(IAdminService) ctx.getBean("adminService");
	@Test
	public void testInsert() {
		Admin admin=new Admin();
		admin.setEmail("admin7@qq.com");
		admin.setPassword("123456");
		admin.setNickName("nadmin7");
		admin.setName("admin7");
		try {
			//System.out.println(this.adminService.insert(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangeLevel() {
		Admin admin=new Admin();
		admin.setEmail("admin7@qq.com");
		admin.setPassword("123456");
		admin.setNickName("nadmin7");
		admin.setName("admin7");
		admin.setLevel(3);
		admin.setId(6);
		try {
			//System.out.println(this.adminService.changeLevel(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		Admin admin=new Admin();
		admin.setEmail("admin7@qq.com");
		admin.setPassword("123456s");
		admin.setNickName("nadmin7");
		admin.setName("admin7");
		admin.setLevel(3);
		admin.setId(6);
		try {
			System.out.println(this.adminService.login(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangePhoto() {
		Admin admin=new Admin();
		admin.setId(1);
		admin.setEmail("admin7@qq.com");
		admin.setPassword("123456s");
		admin.setNickName("nadmin7");
		admin.setName("admin7");
		admin.setPhotoPath("fff");
		admin.setLevel(3);
		admin.setId(6);
		try {
			System.out.println(this.adminService.changePhoto(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangePassword() {
		Admin admin=new Admin();
		admin.setId(1);
		admin.setEmail("admin7@qq.com");
		admin.setPassword("123456ts");
		try {
			System.out.println(this.adminService.changePassword(admin, "1234"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddBoard() {
		Board board=new Board();
		board.setName("java 程序设计");
		board.setDescription("优秀的MVC框架");
		board.setParentId(6);
		board.setAid(1);
		try {
			System.out.println(adminService.addBoard(board));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRemoveBoard() {
		fail("Not yet implemented");
	}
	@Test
	public void testlist() {
		try {
			System.out.println(adminService.list("id", "1", 1, 3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
