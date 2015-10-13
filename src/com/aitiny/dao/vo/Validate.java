package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Validate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String uuid;
	private Integer type;
	private String message;
	private Integer uid;
	private Integer aid;
	private Date time;
	private String valicode;
	
	public Validate() {
		// TODO Auto-generated constructor stub
	}
	
	public Validate(String uuid, Integer type, String message, Integer uid,
			Integer aid, Date time, String valicode) {
		super();
		this.uuid = uuid;
		this.type = type;
		this.message = message;
		this.uid = uid;
		this.aid = aid;
		this.time = time;
		this.valicode = valicode;
	}

	@Override
	public String toString() {
		return "Validate [id=" + id + ", uuid=" + uuid + ", type=" + type
				+ ", message=" + message + ", uid=" + uid + ", aid=" + aid
				+ ", time=" + time + ", valicode=" + valicode + "]";
	}

	public String getValicode() {
		return valicode;
	}

	public void setValicode(String valicode) {
		this.valicode = valicode;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
