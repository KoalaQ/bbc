package com.aitiny.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aitiny.dao.impl.UserDAOImpl;

public class TestUser {
	@Test
	public void test() {
		try {
			 System.out.println("fdsafd");
				ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
				
				System.out.println(ctx); 
				JdbcTemplate jdbcTemplate=	(JdbcTemplate) ctx.getBean("jdbcTemplate");
				UserDAOImpl udao=(UserDAOImpl) ctx.getBean("userDAO");
				 System.out.println(udao);
				System.out.println( jdbcTemplate);
				BasicDataSource bd= (BasicDataSource) ctx.getBean("dataSource");
				System.out.println(bd.getPassword()+","+bd.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
