package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private Integer uid;
	private Integer type;
	private Integer parentid;//评论父节点，如果是文章评论则为pid.。
	private Integer nextid;
	private String content;
	private Date time;
	private Integer counts;
	private Integer rootid;//评论根节点，如果是文章评论则为pid.。如果是子评论则为此评论文章id
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	
	public Reply(Integer pid, Integer uid, Integer type, Integer parentid,
			String content, Date time, Integer rootid) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.type = type;
		this.parentid = parentid;
		this.content = content;
		this.time = time;
		this.rootid = rootid;
	}





	@Override
	public String toString() {
		return "Reply [id=" + id + ", pid=" + pid + ", uid=" + uid + ", type="
				+ type + ", parentid=" + parentid + ", nextid=" + nextid
				+ ", content=" + content + ", time=" + time + ", counts="
				+ counts + ", rootid=" + rootid + "]";
	}


	public Integer getRootid() {
		return rootid;
	}


	public void setRootid(Integer rootid) {
		this.rootid = rootid;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Integer getNextid() {
		return nextid;
	}
	public void setNextid(Integer nextid) {
		this.nextid = nextid;
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


	public Integer getCounts() {
		return counts;
	}


	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	
}
