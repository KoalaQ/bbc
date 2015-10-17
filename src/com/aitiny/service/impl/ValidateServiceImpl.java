package com.aitiny.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;
import com.aitiny.service.IValidateService;
import com.aitiny.service.PushService;
import com.aitiny.service.pushSupport.MailSupport;
import com.aitiny.util.EnumConstant;
import com.aitiny.util.StringUtil;
@Service("validateService")
public class ValidateServiceImpl implements IValidateService {
	@Autowired
	@Qualifier("validateDAO")
	private IValidateDAO validateDAO;
	@Autowired
	@Qualifier("pushService")
	private PushService pushService;
	@Autowired
	@Qualifier("mailPushSupport")
	private MailSupport mailSupport;
	@Override
	public boolean userSendCode(User user) throws Exception {
		// TODO Auto-generated method stub
		
		
		pushService.setPushSupport(mailSupport);
		String code=this.createCode();
		Validate validate=new Validate(StringUtil.getUUID(), EnumConstant.Validate_type_code, code, user.getId(), null, new Date(), code);
		if(pushService.pushMessage(user.getEmail(), "验证码为:"+code+"。用于修改密码，有效时间为30分钟。打死都不能告诉别人。<a href='http://www.aitiny.com'>【www.aitiny.com】 <a/>")){	
			if(validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_code)==null){
				return	validateDAO.doCreate(validate);		 
			}else{
				validateDAO.doRemove(validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_code).getId());//先清除以前的验证码
				return	validateDAO.doCreate(validate);
			}
			
		}
		return false;
	}

	@Override
	public boolean userSendUrl(User user) throws Exception {
		// TODO Auto-generated method stub
		
	
		pushService.setPushSupport(mailSupport);
		String[] url=this.createUrl();
		Validate validate=new Validate(url[1], EnumConstant.Validate_type_url, url[0], user.getId(), null, new Date(),null);
		if(pushService.pushMessage(user.getEmail(), "点击链接激活账号：<a href='"+url[0]+"'>"+url[0]+"<a/>。用于验证邮箱，有效时间为30分钟。打死都不能告诉别人。<a href='www.aitiny.com'>【www.aitiny.com】 <a/>")){	
			if(validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_url)==null){
				return	validateDAO.doCreate(validate);		 
			}else{
				validateDAO.doRemove(validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_url).getId());//先清除以前的验证码
				return	validateDAO.doCreate(validate);
			}	 
		}
		return false;
	}

	@Override
	public boolean adminSendCode(Admin admin) throws Exception {
		// TODO Auto-generated method stub
	
		pushService.setPushSupport(mailSupport);
		String code=this.createCode();
		Validate validate=new Validate(StringUtil.getUUID(), EnumConstant.Validate_type_code, code, null,admin.getId(), new Date(), code);
		if(pushService.pushMessage(admin.getEmail(), "验证码为:"+code+"。用于修改密码，有效时间为30分钟。打死都不能告诉别人。<a href='www.aitiny.com'>【www.aitiny.com】 <a/>")){	
			if(validateDAO.findByUidAndTpye(admin.getId(), EnumConstant.Validate_type_code)==null){
				return	validateDAO.doCreate(validate);		 
			}else{
				validateDAO.doRemove(validateDAO.findByUidAndTpye(admin.getId(), EnumConstant.Validate_type_code).getId());//先清除以前的验证码
				return	validateDAO.doCreate(validate);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Date time) throws Exception {
		// TODO Auto-generated method stub
		return this.validateDAO.doRemove(time);
	}
	
	@Override
	public boolean remove(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.validateDAO.doRemove(id);
	}

	private String createCode(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<6;i++){
			sb.append((int)(Math.random()*10));
		}
		return sb.toString();
	}
	/**
	 * 可以写入到资源文件，读取url模板。这里为了省事就直接写啦
	 * @return 
	 */
	private String[] createUrl(){		
		String uuid=StringUtil.getUUID();
		return new String[]{"www.aitiny.com/user/check/"+uuid,uuid};
	}
}
