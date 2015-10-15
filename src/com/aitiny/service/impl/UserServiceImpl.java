package com.aitiny.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;
import com.aitiny.service.IUserService;
import com.aitiny.util.Encode;
import com.aitiny.util.EnumConstant;
import com.aitiny.util.StringUtil;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	@Qualifier("userDAO")
	private IUserDAO userDAO; 
	@Autowired
	@Qualifier("validateDAO")
	private IValidateDAO validateDAO;
	@Override
	public int login(User user) throws Exception {
		// TODO Auto-generated method stub
		if(this.userDAO.findByEmail(user.getEmail())==null){
			return EnumConstant.User_Status_unknow;//用户不存在
		}
		User u=this.userDAO.findByEmail(user.getEmail());
		if(u.getStatus()==EnumConstant.User_Status_lock){
			return EnumConstant.User_Status_lock; //用户被锁定
		}
		if(u.getStatus()==EnumConstant.Usre_status_unverified){
			return EnumConstant.Usre_status_unverified; //用户邮箱未验证
		}
		if(u.getPassword().equals(Encode.getEncode(Encode.MD5, user.getPassword()))){
			if(user.getUuid().equals(u.getUuid()))
			{
				this.userDAO.doUpdate(u.getId(), new String[]{"status"},new Object[]{EnumConstant.User_Status_online});
			}else{
				this.userDAO.doUpdate(u.getId(), new String[]{"status","uuid"},new Object[]{EnumConstant.User_Status_online,StringUtil.getUUID()});
			}
			return EnumConstant.User_Status_online;
		}
		return EnumConstant.User_Wrong_password;
	}

	@Override
	public User getUser(String email) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.findByEmail(email);
	}

	@Override
	public boolean logout(User user) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.doUpdate(user.getId(), new String[]{"status"}, new Object[]{EnumConstant.User_status_ofline});
	}

	@Override
	public boolean hasEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.findByEmail(email)!=null;
	}

	@Override
	public boolean hasNickName(String nickName) throws Exception {
		// TODO Auto-generated method stub
		return  this.userDAO.afindByColumnsCounts(new String[]{"nickName"}, new Object[]{nickName})>0;
	}

	@Override
	public boolean register(User user) throws Exception {
		// TODO Auto-generated method stub
		if(this.hasEmail(user.getEmail()) || this.hasNickName(user.getNickName())    ){
			return false;
		}
		return this.userDAO.doCreate(user);
	}

	@Override
	public boolean changePassword(User user, String valiCode) throws Exception {
		// TODO Auto-generated method stub
		if(this.validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_code)==null){
			return false;
		}
		Validate validate=this.validateDAO.findByUidAndTpye(user.getId(), EnumConstant.Validate_type_code);
		if(validate.getValicode().equals(valiCode)){
			if(this.userDAO.doUpdate(user.getId(), new String[]{"password"}, new Object[]{user.getPassword()})){
				this.validateDAO.doRemove(validate.getId());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		if(this.hasNickName(user.getNickName())  && !this.userDAO.findById(user.getId()).getNickName().equals(user.getNickName())){
			//System.out.println("updateInfo false");
			return false;
		}
		return this.userDAO.doUpdate(user);
	}

	@Override
	public boolean addVantages(int id,int vantages) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.doUpdate(id, new String[]{"vantages"},
				new Object[]{this.userDAO.findById(id).getVantages()+vantages});
	}

}
