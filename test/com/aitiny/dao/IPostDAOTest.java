package com.aitiny.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Post;

public class IPostDAOTest {
ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
IPostDAO postDAO=(IPostDAO) ctx.getBean("postDAO");
	@Test
	public void testDoCreate() {
		Post post=new Post(1, 3, null, "test", "testContent", new Date(), "testFiles", "testSummary", "testTag","pp");
		try {
			System.out.println(this.postDAO.doCreate(post));
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
		try {
			System.out.println(this.postDAO.findById(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAll() {
		try {
			System.out.println(this.postDAO.findAllAvailable(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllStringStringIntegerInteger() {
		try {
			System.out.println(this.postDAO.findAllAvailable("id", "", 2, 2, 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllStringStringIntegerIntegerStringInteger() {
		try {
			System.out.println(this.postDAO.findAllAvailable("id", "", 1, 2,"id",1, 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCount() {
		try {
			System.out.println(this.postDAO.getAllCountAvailable("id", "", 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
