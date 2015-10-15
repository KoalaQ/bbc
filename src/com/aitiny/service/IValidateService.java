package com.aitiny.service;

import java.util.Date;

public interface IValidateService {
		public boolean userSendCode()throws Exception;
		public boolean userSendUrl()throws Exception;
		public boolean adminSendCode()throws Exception;
		public boolean remove(Date time)throws Exception;
		
}
