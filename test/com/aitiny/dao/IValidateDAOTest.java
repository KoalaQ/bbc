package com.aitiny.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Validate;
import com.aitiny.util.StringUtil;

public class IValidateDAOTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IValidateDAO validateDAO=(IValidateDAO) ctx.getBean("validateDAO");
	@Test
	public void testDoCreate() {
		Validate validate=new Validate( StringUtil.getUUID(),2,"test", 1, null, new Date());
		try {
			System.out.println(this.validateDAO.doCreate(validate));
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
			System.out.println(this.validateDAO.findById(2));
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
