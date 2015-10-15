package com.aitiny.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aitiny.dao.vo.Message;
import com.aitiny.util.EnumConstant;

public class IMessageServiceTest {
	ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	IMessageService messageService=(IMessageService) ctx.getBean("messageService");
	@Test
	public void testSend() {
		Message message=new Message(1, 3, "test"+ new Date(), new Date(), EnumConstant.Message_type_new);
		try {
			System.out.println(this.messageService.send(message));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testHasRead() {
	try {
		System.out.println(this.messageService.hasRead(new int[]{1,2,7}));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Test
	public void testDelete() {
		try {
			System.out.println(this.messageService.delete(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetMessages() {
		try {
			System.out.println(this.messageService.getMessages(1, 1, 1, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNewMessages() {
		try {
			System.out.println(this.messageService.getNewMessages(1, 1, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
