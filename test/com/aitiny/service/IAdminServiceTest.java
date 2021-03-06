package com.aitiny.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.Board;
import com.aitiny.dao.vo.Log;
import com.aitiny.util.EnumConstant;

public class IAdminServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IAdminService adminService=(IAdminService) ctx.getBean("adminService");
	@Test
	public void testInsert() {
//		Log log=new Log();
//		System.out.println(log.getAid()+","+log.getUid());
		Admin admin=new Admin();
		System.out.println(admin.getEmail());
		admin.setId(1);
		admin.setEmail("admin10@qq.com");
		admin.setPassword("123456");
		admin.setNickName("nadmin10");
		admin.setName("admin10");
		admin.setLevel(EnumConstant.Admin_Level_High);
		Admin insertAdmin=new Admin();
		System.out.println(admin.getEmail());
		insertAdmin.setId(2);
		insertAdmin.setEmail("admin10@qq.com");
		insertAdmin.setPassword("123456");
		insertAdmin.setNickName("nadmin10");
		insertAdmin.setName("admin10");
		try {
			System.out.println(this.adminService.insert(admin, insertAdmin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testChangeLevel() {
		Admin admin=new Admin();
		admin.setLevel(EnumConstant.Admin_Level_High);
		admin.setId(1);
		Admin insertAdmin=new Admin();
		//System.out.println(admin.getEmail());
		insertAdmin.setId(1);
		insertAdmin.setLevel(3);
		try {
			System.out.println(this.adminService.changeLevel(admin, insertAdmin));
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
		admin.setPassword("123456");
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
		admin.setPhotoPath("ffgfgf");
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

	
}
