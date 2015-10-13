package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 每个用户只能拥有一个（id,type）相同的验证，双主键。拥有后只能做更新即选择啦再次获取，或者过期删除
 * @author koala
 *
 */
@SuppressWarnings("serial")
public class Message implements Serializable {
	private Integer id;
	private Integer fromUser;
	private Integer toUser;
	private String content;
	private Date time;
	private Integer status;
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(Integer fromUser, Integer toUser, String content, Date time,
			Integer status) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.content = content;
		this.time = time;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", fromUser=" + fromUser + ", toUser="
				+ toUser + ", content=" + content + ", time=" + time
				+ ", status=" + status + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromUser() {
		return fromUser;
	}

	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}