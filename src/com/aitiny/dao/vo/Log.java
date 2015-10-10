package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Log implements Serializable {
	private Integer id;
	private Integer aid;
	private Integer uid;
	private String content;
	private Date time;
	private String name;
	@Override
	public String toString() {
		return "Log [id=" + id + ", aid=" + aid + ", uid=" + uid + ", content="
				+ content + ", time=" + time + ", name=" + name + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getcontent() {
		return content;
	}
	public void setcontent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
