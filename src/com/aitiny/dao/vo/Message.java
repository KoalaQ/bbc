package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Message implements Serializable {
	private Integer id;
	private Integer from;
	private Integer to;
	private String content;
	private Date time;
	private Integer status;
	@Override
	public String toString() {
		return "Message [id=" + id + ", from=" + from + ", to=" + to
				+ ", content=" + content + ", time=" + time + ", status="
				+ status + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
