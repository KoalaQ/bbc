package com.aitiny.service;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Post;
import com.aitiny.dao.vo.Reply;

public class IPostServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IPostService postService=(IPostService) ctx.getBean("postService");
	@Test
	public void testPostBlog() {
		Post post=new Post(1, 1, 1, "test", "ceshi", new Date(), "", "ceshi", "ceshi");
		try {
			System.out.println(this.postService.postBlog(post));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testModifyBlog() {
		Post post=new Post(1, 1, 1, "test3", "cesh4", new Date(), "", "ceggrs2hi", "grces2grhi");
		post.setId(1);
		try {
			System.out.println(this.postService.modifyBlog(post));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testRecycleBlog() {
		try {
			System.out.println(this.postService.deleteBlog(6));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteBlog() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBlog() {
		try {
			System.out.println(this.postService.getBlog(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListPost() {
	try {
		System.out.println(this.postService.listPost("id", "", 2, 2, "id", 1,0));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testAddReply() {
	try {
		this.postService.addReply(new Reply(1, 1, 1, 1, "fdsa", new Date(), 0));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
