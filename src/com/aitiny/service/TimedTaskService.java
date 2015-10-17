package com.aitiny.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component("timeService")
public class TimedTaskService {
	@Autowired
	@Qualifier("topService")
	private ITopService topService;
	@Autowired
	@Qualifier("messageService")
	private IMessageService messageService;
	@Autowired
	@Qualifier("validateService")
	private IValidateService validateService;
	//
	@Scheduled(cron = "0 0/15 * * * ?")
	public void refreshTop(){
		System.out.println("hello");
		try {
			topService.refreshTop(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//每3个小时清理一次
	@Scheduled(cron = "0 0 0/3 * * ?")
	public void clearMessage(){
		System.out.println("hello");
		try {
			messageService.delete(new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//每5分钟检查一次，当前时间的半小时前。所以会有一些小出入
	@Scheduled(cron = "0 0/5 * * * ?")
	public void clearValidate(){
		System.out.println("hello");
		try {
			validateService.remove(new Date(new Date().getTime()- 30*60*1000));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void pushMessage(){
		
	}
}
