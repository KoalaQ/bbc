package com.aitiny.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Fans;

public class IFansDAOTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IFansDAO fansDAO=(IFansDAO) ctx.getBean("fansDAO");
	@Test
	public void testDoCreate() {
		Fans fans=new Fans(1, 1, 1, new Date());
		try {
			System.out.println(this.fansDAO.doCreate(fans));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoUpdateV() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoUpdateKStringArrayObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoRemove() {
		try {
			this.fansDAO.doRemove(1, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindById() {
		try {
			System.out.println(this.fansDAO.findById(1));
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
