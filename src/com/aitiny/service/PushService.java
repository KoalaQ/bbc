package com.aitiny.service;

import org.springframework.stereotype.Service;
/**
 * 整个服务现在就使用验证一个。整体完成后在优化
 * @author koala
 *
 */
@Service("pushService")
public class PushService {
	private IPushSupport pushSupport;
	public void setPushSupport(IPushSupport pushSupport){
		this.pushSupport=pushSupport;
	}
	public boolean pushMessage(String to,String message){
		return pushSupport.pushMessage(to, message);
		
	}
}
