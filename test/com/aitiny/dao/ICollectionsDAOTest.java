package com.aitiny.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Collections;

public class ICollectionsDAOTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	ICollectionsDAO collectionsDAO=(ICollectionsDAO) ctx.getBean("collectionsDAO");
	@Test
	public void testDoCreate() {
		Collections collections=new Collections(1, 2, new Date(), "test", "test");
		try {
			System.out.println(this.collectionsDAO.doCreate(collections));
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
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
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
