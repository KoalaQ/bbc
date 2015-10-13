package com.aitiny.dao.vo;

import java.io.Serializable;

public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String password;
	private Integer level;
	private String nickName;
	private String name;
	private String photoPath;

	public Admin() {
		super();
	}
	public Admin(String email, String password, String nickName, String name) {
		super();
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", password="
				+ password + ", level=" + level + ", nickName=" + nickName
				+ ", name=" + name + ", photoPath=" + photoPath + "]";
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
