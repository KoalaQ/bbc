package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Top implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private Date time;
	public Top() {
		// TODO Auto-generated constructor stub
	}
	public Top(Integer id, Integer pid, Date time) {
		super();
		this.id = id;
		this.pid = pid;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Top [id=" + id + ", pid=" + pid + ", time=" + time + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
}
