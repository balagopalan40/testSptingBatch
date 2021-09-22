package com.finance.model;

import java.io.Serializable;

public class Post implements Serializable{
	
	public Post(int i, String string, String string2) {
		// TODO Auto-generated constructor stub
		this.id =i;
		this.title = string;
		this.body = string2;
	}
	private int userId;
    private int id;
    private String title;
    private String body;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
    
    

}
