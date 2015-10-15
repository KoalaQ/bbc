package com.aitiny.service;

public interface IPushService {
	public void setPushSupport(IPushService pushSupport);
	public boolean pushMessage(String to,String message);
}
