package com.aitiny.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Top;

public class ITopDAOTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    ITopDAO topDAO=(ITopDAO) ctx.getBean("topDAO");
	@Test
	public void testDoCreate() {
		Top top=new Top(1, 1, new Date());
		try {
			System.out.println(this.topDAO.doCreate(top));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoUpdateV() {
		Top top=new Top(1, 2, new Date());
		try {
			System.out.println(this.topDAO.doUpdate(top));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoUpdateKStringArrayObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		try {
			System.out.println(this.topDAO.findById(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllStringStringIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllStringStringIntegerIntegerStringInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllCount() {
		fail("Not yet implemented");
	}

}
