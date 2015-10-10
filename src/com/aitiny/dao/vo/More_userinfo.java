package com.aitiny.dao.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class More_userinfo implements Serializable {
	private Integer id;
	private Integer uid;
	private Integer checked;
	private Integer isvip;
	@Override
	public String toString() {
		return "More_userinfo [id=" + id + ", uid=" + uid + ", checked="
				+ checked + ", isvip=" + isvip + "]";
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
	public Integer getChecked() {
		return checked;
	}
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	public Integer getIsvip() {
		return isvip;
	}
	public void setIsvip(Integer isvip) {
		this.isvip = isvip;
	}
	 
}
