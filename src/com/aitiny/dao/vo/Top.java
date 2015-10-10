package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Top implements Serializable {
	private Integer id;
	private Integer pid;
	private Date time;
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
