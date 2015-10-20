package com.aitiny.service.pushSupport;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.aitiny.service.IPushSupport;
@Service("mailPushSupport")
public class MailSupport implements IPushSupport{
	public  boolean sendMail(String title,String content,String to){
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost("smtp.163.com");
		//senderImpl.setPort(465);
		//senderImpl.setUsername("1399522085@qq.com");
		//senderImpl.setPassword("LYD147258369");
		senderImpl.setUsername("ai_tiny@163.com");
		senderImpl.setPassword("cayyittouqhwbyln");
		// 建立HTML邮件消息
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// true表示开始附件模式
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			messageHelper.setFrom("ai_tiny@163.com");
			// 设置收件人，寄件人
			messageHelper.setTo(to);
			messageHelper.setSubject(title);
			// true 表示启动HTML格式的邮件
			messageHelper.setText(content, true);
			senderImpl.send(mailMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

		//FileSystemResource file1 = new FileSystemResource(new File("d:/pp.jpeg"));
		//FileSystemResource file2 = new FileSystemResource(new File("d:/读书.txt"));
		// 添加2个附件
//		messageHelper.addAttachment("pp.jpg", file1);
//		
//		try {
//			//附件名有中文可能出现乱码
//			messageHelper.addAttachment(MimeUtility.encodeWord("读书.txt"), file2);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			throw new MessagingException();
//		}
		// 发送邮件
	
	}

	@Override
	public boolean pushMessage(String to, String message) {
		// TODO Auto-generated method stub
		System.out.println("MailSupport.pushMessage:"+to+","+message);
		return this.sendMail("验证邮件", message, to);
	}


}
