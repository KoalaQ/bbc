package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Fans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private Integer fuid;
	private Integer type;
	private Date time;
	
public Fans() {
	// TODO Auto-generated constructor stub
}
	public Fans(Integer uid, Integer fuid, Integer type, Date time) {
		super();
		this.uid = uid;
		this.fuid = fuid;
		this.type = type;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Fans [id=" + id + ", uid=" + uid + ", fuid=" + fuid + ", type="
				+ type + ", time=" + time + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getFuid() {
		return fuid;
	}
	public void setFuid(Integer fuid) {
		this.fuid = fuid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
