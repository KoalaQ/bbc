package com.aitiny.dao.vo;

import java.io.Serializable;

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
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", nickName=" + nickName + ", photoPath=" + photoPath
				+ ", available=" + available + ", vantages=" + vantages
				+ ", viewcount=" + viewcount + ", postcount=" + postcount
				+ ", grade=" + grade + ", fanscount=" + fanscount
				+ ", concerncount=" + concerncount + ", theme=" + theme + "]";
	}
	public Integer getId() {
		return id;
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



	public Integer getvantages() {
		return vantages;
	}



	public void setvantages(Integer vantages) {
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



	public Integer getconcerncount() {
		return concerncount;
	}



	public void setconcerncount(Integer concerncount) {
		this.concerncount = concerncount;
	}



	public Integer getTheme() {
		return theme;
	}



	public void setTheme(Integer theme) {
		this.theme = theme;
	}
	
}
