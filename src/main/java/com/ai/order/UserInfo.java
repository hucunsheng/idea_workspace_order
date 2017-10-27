package com.ai.order;


public class UserInfo {
	private String userName;
	private String createDate;
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(String userName, String createDate) {
		super();
		this.userName = userName;
		this.createDate = createDate;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	

}
