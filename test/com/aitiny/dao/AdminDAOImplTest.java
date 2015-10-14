package com.aitiny.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Admin;

public class AdminDAOImplTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IAdminDAO adminDAO=(IAdminDAO) ctx.getBean("adminDAO");
	@Test
	public void testDoCreate() {
		Admin admin=new Admin();
		admin.setEmail("admin5@qq.com");
		admin.setPassword("123456");
		admin.setNickName("nadmin5");
		admin.setName("admin5");
		try {
			System.out.println(adminDAO.doCreate(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoUpdateAdmin() {
		Admin admin=new Admin();
		admin.setId(1);
		admin.setEmail("admin3@qq.com");
		admin.setPassword("123456");
		admin.setLevel(2);
		admin.setNickName("nadmin6");
		admin.setName("admin6");
		admin.setPhotoPath("c:/");
		try {
			System.out.println(adminDAO.doUpdate(admin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindByEmail() {
			try {
				System.out.println(adminDAO.findByEmail("admin3@qq.com"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Test
	public void testDoUpdate() {
		try {
//			System.out.println(adminDAO.doUpdate(1, "email", "jqq1.@jcong"));
//			System.out.println(adminDAO.doUpdate(1, "password", "jqq1.@jcong"));
//			System.out.println(adminDAO.doUpdate(1, "level", "10"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindByNickName() {
	try {
		System.out.println(adminDAO.findByNickName("nadmin6"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Test
	public void testFindByName() {
	try {
		System.out.println(adminDAO.findByName("admin6"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testDoRemove() {
		try {
			System.out.println(adminDAO.doRemove(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindById() {
	try {
		System.out.println(adminDAO.findById(133));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testFindAll() {
		try {
			System.out.println(adminDAO.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllfenye() {
		try {
			System.out.println(adminDAO.findAll("id", "", 2, 3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllfenyeOrder() {
		try {
			System.out.println(adminDAO.findAll("id", "", 2, 2, "id", 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCount() {
		try {
			System.out.println(adminDAO.getAllCount("email", "jq0101q"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
