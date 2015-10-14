package com.aitiny.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.aitiny.dao.IMessageDAO;
import com.aitiny.dao.vo.Message;
import com.aitiny.service.AService;
import com.aitiny.service.IMessageService;
import com.aitiny.util.EnumConstant;
@Service("messageService")
public class MessageServiceImpl extends AService<Message> implements IMessageService {
	@Autowired
	@Qualifier("messageDAO")
	private IMessageDAO messageDAO;
	@Override
	protected void initDAO() {
		// TODO Auto-generated method stub
		this.findDAO=messageDAO;
	}
	@Override
	public boolean send(Message message) throws Exception {
		// TODO Auto-generated method stub
		return messageDAO.doCreate(message);
	}

	@Override
	public boolean hasRead(int[] id) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0;i<id.length;i++){
			try {
				messageDAO.doUpdate(id[i], new String[]{"status"}, new Object[]{EnumConstant.Message_type_readed});
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}	
		}
		return true;
	}

	@Override
	public boolean delete(Date time) throws Exception {
		// TODO Auto-generated method stub
		return this.messageDAO.doRemove(time);
	}


	@Override
	public Map<String, Object> getMessages(int uid, int fuid, int currentPage,
			int lineSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.messageDAO.findAll(uid, fuid, currentPage, lineSize));
		map.put("count", this.messageDAO.getAllCount(uid, fuid));
		
		return map;
	}

	@Override
	public Map<String, Object> getNewMessages(int uid, int currentPage,
			int lineSize) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("all", this.messageDAO.findAll(uid, currentPage, lineSize));
		map.put("count", this.messageDAO.getAllCount(uid));
		
		return map;
	}


}
