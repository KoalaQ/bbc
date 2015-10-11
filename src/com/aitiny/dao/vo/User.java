package com.aitiny.dao.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class User implements Serializable {
	private Integer id;
	private String email;
	private String password;
	private String nickName;
	private String photoPath;
	private Integer available;
	private Integer vantages;
	private Integer viewcount;
	private Integer postcount;
	private Integer grade;
	private Integer fanscount;
	private Integer concerncount;
	private Integer theme;
	private Date regtime;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User( String email, String password, String nickName,
			String photoPath, Integer available, Integer vantages,
			Integer viewcount, Integer postcount, Integer grade,
			Integer fanscount, Integer concerncount, Integer theme, Date regtime) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.photoPath = photoPath;
		this.available = available;
		this.vantages = vantages;
		this.viewcount = viewcount;
		this.postcount = postcount;
		this.grade = grade;
		this.fanscount = fanscount;
		this.concerncount = concerncount;
		this.theme = theme;
		this.regtime = regtime;
	}

	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", nickName=" + nickName + ", photoPath=" + photoPath
				+ ", available=" + available + ", vantages=" + vantages
				+ ", viewcount=" + viewcount + ", postcount=" + postcount
				+ ", grade=" + grade + ", fanscount=" + fanscount
				+ ", concerncount=" + concerncount + ", theme=" + theme
				+ ", regtime=" + regtime + "]";
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getVantages() {
		return vantages;
	}
	public void setVantages(Integer vantages) {
		this.vantages = vantages;
	}
	public Integer getViewcount() {
		return viewcount;
	}
	public void setViewcount(Integer viewcount) {
		this.viewcount = viewcount;
	}
	public Integer getPostcount() {
		return postcount;
	}
	public void setPostcount(Integer postcount) {
		this.postcount = postcount;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getFanscount() {
		return fanscount;
	}
	public void setFanscount(Integer fanscount) {
		this.fanscount = fanscount;
	}
	public Integer getConcerncount() {
		return concerncount;
	}
	public void setConcerncount(Integer concerncount) {
		this.concerncount = concerncount;
	}
	public Integer getTheme() {
		return theme;
	}
	public void setTheme(Integer theme) {
		this.theme = theme;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	
	
	
}
