package com.aitiny.beans;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
		public static void main(String[] args) {
			String config="classpath:applicationContext.xml";
				ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
			//	HelloWorld hw=(HelloWorld) ctx.getBean("helloWorld");
				//hw.SayHello();
				System.out.println(ctx);
		}
}
