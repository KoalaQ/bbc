package com.aitiny.dao.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aitiny.dao.ADAO;
import com.aitiny.dao.IValidateDAO;
import com.aitiny.dao.vo.Validate;
import com.aitiny.exception.MethodNotRealize;
import com.aitiny.util.EnumConstant;
@Repository("validateDAO")
public class ValidateDAOImpl extends ADAO<Integer, Validate> implements IValidateDAO {
	@Override
	protected void initADAO() {
		// TODO Auto-generated method stub
		this.cls=Validate.class;
		this.keyName="id";
		this.table="validate";
	}
	@Override
	public boolean doCreate(Validate vo) throws Exception {
		// TODO Auto-generated method stub
		String sql="INSERT INTO validate(uuid,type,message,uid,aid,time,valicode) VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{vo.getUuid(),vo.getType(),vo.getMessage(),vo.getUid(),vo.getAid(),vo.getTime(),vo.getValicode()};
		if(this.jdbcTemplate.update(sql, params)>0){
			return true;
		}
		
		return false;
	}


	@Override
	public boolean doUpdate(Validate vo) throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}

	@Override
	public boolean doUpdate(Integer id, String[] Columns, Object[] value)
			throws Exception {
		// TODO Auto-generated method stub
		throw new MethodNotRealize("该方法未实现");
	}
	@Override
	public Validate findByUUID(String uuid) throws Exception {
		List<Validate> validates=this.afindByColumns(new String[]{"uuid"}, new Object[]{uuid,0,10},"id",EnumConstant.Order_type_ASEC);
		if(validates.size()<0){
			return null;
		}
		return validates.get(0);
	}
	@Override
	public Validate findByAidAndType(Integer aid, Integer type)
			throws Exception {		
		List<Validate> validates=this.afindByColumns(new String[]{"aid","type"}, new Object[]{aid,type,0,10},"id",EnumConstant.Order_type_ASEC);
		if(validates.size()<=0){
			return null;
		}
		return validates.get(0);
	}
	@Override
	public Validate findByUidAndTpye(Integer uid, Integer type)
			throws Exception {
		List<Validate> validates=this.afindByColumns(new String[]{"uid","type"}, new Object[]{uid,type,0,10},"id",EnumConstant.Order_type_ASEC);
		if(validates.size()<=0){
			return null;
		}
		return validates.get(0);
	}
	@Override
	public boolean doRemove(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.adoRemoveByKey(id);
	}
	@Override
	public Validate findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.afindByKey(id);
	}
	@Override
	public List<Validate> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.afindAll();
	}
	@Override
	public List<Validate> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize);
	}
	@Override
	public List<Validate> findAll(String column, String keyWord,
			Integer currentPage, Integer lineSize, String orderColumn,
			Integer orderType) throws Exception {
		// TODO Auto-generated method stub
		return this.afindPaging(column, keyWord, currentPage, lineSize, orderColumn, orderType);
	}
	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return this.agetPagingCount(column, keyWord);
	}


	
}
