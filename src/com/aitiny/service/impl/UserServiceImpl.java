package com.aitiny.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.ICollectionsDAO;
import com.aitiny.dao.IFansDAO;
import com.aitiny.dao.IUserDAO;
import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.Collections;
import com.aitiny.dao.vo.Fans;
import com.aitiny.dao.vo.User;
import com.aitiny.dao.vo.Validate;
import com.aitiny.service.AService;
import com.aitiny.service.IUserService;
import com.aitiny.util.Encode;
import com.aitiny.util.EnumConstant;
import com.aitiny.util.StringUtil;
@Service("userService")
public class UserServiceImpl extends AService<User> implements IUserService {
	@Autowired
	@Qualifier("userDAO")
	private IUserDAO userDAO; 
	@Autowired
	@Qualifier("validateDAO")
	private IValidateDAO validateDAO;
	@Autowired
	@Qualifier("fansDAO")
	private IFansDAO fansDAO;
	@Autowired
	@Qualifier("collectionsDAO")
	private ICollectionsDAO collectionsDAO;
	@Override
	protected void initDAO() {
		// TODO Auto-generated method stub
		this.mapDAO=this.userDAO;
	}
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

	@Override
	public boolean checkEmail(User user) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.doUpdate(user.getId(), new String[]{"status"}, new Object[]{EnumConstant.Usre_status_verified});
	}

	@Override
	public User findUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.userDAO.findById(id);
	}

	@Override
	public Map<String, Object> listUser(String column, String keyWord,
			int currentPage, int lineSize, String orderColumn, int orderType)
			throws Exception {
		// TODO Auto-generated method stub
		this.setMapDAO(userDAO);
		return this.listOrder(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}

	@Override
	public boolean addFans(Fans fans) throws Exception {
		// TODO Auto-generated method stub
		if(this.fansDAO.doCreate(fans)){
			this.userDAO.doUpdate(fans.getUid(), new String[]{"fanscount"} , new Object[]{this.userDAO.findById(fans.getUid()).getFanscount()+1});
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteFans(Fans fans) throws Exception {
		// TODO Auto-generated method stub
		return this.fansDAO.doRemove(fans.getUid(),fans.getFuid());
	}
	@Override
	public Map<String, Object> listFans(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		this.setMapDAO(fansDAO);
		return this.list(column, keyWord, currentPage, lineSize);
	}
	@Override
	public boolean addCollect(Collections coll) throws Exception {
		// TODO Auto-generated method stub
		return this.collectionsDAO.doCreate(coll);
	}
	@Override
	public boolean deleteCollect(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.collectionsDAO.doRemove(id);
	}
	@Override
	public Map<String, Object> listCollections(String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		// TODO Auto-generated method stub
		this.setMapDAO(collectionsDAO);
		return this.list(column, keyWord, currentPage, lineSize);
	}

	

}
