package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Collections implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer uid;
	private Integer pid;
	private Date time;
	private String title;
	private String summary;
	
	public Collections() {
		// TODO Auto-generated constructor stub
	}
	public Collections(Integer uid, Integer pid, Date time, String title,
			String summary) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.time = time;
		this.title = title;
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "Collections [id=" + id + ", uid=" + uid + ", pid=" + pid
				+ ", time=" + time + ", title=" + title + ", summary="
				+ summary + "]";
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}	
