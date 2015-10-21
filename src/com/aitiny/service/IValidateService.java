package com.aitiny.service;

import java.util.Date;

import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;

public interface IValidateService {
		public boolean userSendCode(User user)throws Exception;
		/**
		 * 现在只需要发送邮件有url，进行邮箱验证。功能单一，第一次不进行强化。进一步需要传入模板。发送广告，精彩消息等
		 * 
		 * @param user
		 * @return
		 * @throws Exception
		 */
		public boolean userSendUrl(User user)throws Exception;
		public boolean adminSendCode(Admin admin)throws Exception;
		public boolean remove(Date time)throws Exception;
		public boolean remove(int  id)throws Exception;
		public boolean checkUserByUuid(String uuid) throws Exception;
		
}
