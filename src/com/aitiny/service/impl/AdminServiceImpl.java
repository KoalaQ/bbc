package com.aitiny.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IAdminDAO;
import com.aitiny.dao.IBoardDAO;
import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.Admin;
import com.aitiny.dao.vo.Board;
import com.aitiny.dao.vo.Validate;
import com.aitiny.exception.MethodNotRealize;
import com.aitiny.service.AService;
import com.aitiny.service.IAdminService;
import com.aitiny.util.Encode;
import com.aitiny.util.EnumConstant;
@Service("adminService")
public class AdminServiceImpl extends AService<Admin> implements IAdminService {
	@Autowired
	@Qualifier("adminDAO")
	private IAdminDAO adminDAO;
	@Autowired
	@Qualifier("validateDAO")
	private IValidateDAO validateDAO;

	@Override
	protected void initDAO() {
	}
	@Override
	public boolean insert(Admin admin,Admin insertAdmin) throws Exception {
		// TODO Auto-generated method stub
		if(admin.getLevel()!=EnumConstant.Admin_Level_High){
			return false;
		}
		return adminDAO.doCreate(insertAdmin);
	}

	@Override
	public boolean changeLevel(Admin admin,Admin insertAdmin) throws Exception {
		// TODO Auto-generated method stub
		if(admin.getLevel()!=EnumConstant.Admin_Level_High || insertAdmin.getLevel()==EnumConstant.Admin_Level_High){
			return false;
		}
		return adminDAO.doUpdate(insertAdmin.getId(), new String[]{"level"}, new Object[]{insertAdmin.getLevel()});
	}

	@Override
	@Deprecated
	public boolean updateInfo(Admin admin,Admin insertAdmin) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}

	@Override
	public boolean login(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		if(adminDAO.findByEmail(admin.getEmail()).getPassword().equals(Encode.getEncode(Encode.MD5, admin.getPassword()))){
			return true;
		}
		return false;
	}

	@Override
	public boolean changePhoto(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return this.adminDAO.doUpdatePhoto(admin.getId(), admin.getPhotoPath());
	}

	@Override
	public boolean changePassword(Admin admin, String validateInfo)
			throws Exception {
		// TODO Auto-generated method stub
		if(this.validateDAO.findByAidAndType(admin.getId(), EnumConstant.Validate_type_code)==null){
			return false;
		}
		Validate validate=this.validateDAO.findByAidAndType(admin.getId(), EnumConstant.Validate_type_code);
		if(validate.getValicode().equals(validateInfo)){
			if( this.adminDAO.doUpdatePassword(admin.getId(), admin.getPassword())){
				validateDAO.doRemove(validate.getId());
				return true;
			}
		}
		return false;
	}
	@Override
	public Admin findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return this.adminDAO.findByEmail(email);
	}

	



}
