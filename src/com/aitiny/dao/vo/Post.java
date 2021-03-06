package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Post implements Serializable {
	private Integer id;
	private Integer bid;
	private Integer uid;
	private Integer aid;
	private String name;
	private String content;
	private Date publishTime;
	private Integer viewcount;
	private Integer likes;
	private Integer status;
	private String files;
	private  String summary;
	private String tag;
	private String author;
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	public Post(Integer bid, Integer uid, Integer aid, String name,
			String content, Date publishTime, String files, String summary,
			String tag,String author) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.aid = aid;
		this.name = name;
		this.content = content;
		this.publishTime = publishTime;
		this.files = files;
		this.summary = summary;
		this.tag = tag;
		this.author=author;
	}

	

	@Override
	public String toString() {
		return "Post [id=" + id + ", bid=" + bid + ", uid=" + uid + ", aid="
				+ aid + ", name=" + name + ", content=" + content
				+ ", publishTime=" + publishTime + ", viewcount=" + viewcount
				+ ", likes=" + likes + ", status=" + status + ", files="
				+ files + ", summary=" + summary + ", tag=" + tag + ", author="
				+ author + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
