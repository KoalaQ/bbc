package com.aitiny.dao.vo;

import java.io.Serializable;

public class Fans implements Serializable {
	private Integer id;
	private Integer uid;
	private Integer fuid;
	private Integer type;
	@Override
	public String toString() {
		return "Fans [id=" + id + ", uid=" + uid + ", fuid=" + fuid + ", type="
				+ type + "]";
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
	
}
