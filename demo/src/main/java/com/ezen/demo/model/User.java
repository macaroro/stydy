package com.ezen.demo.model;

import java.util.Objects;

public class User {
	private String uid;
	private String upw;
	public User(String[] token) {
		this(token[0],token[1]);
	  
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	@Override
	public int hashCode() {
		
		return Objects.hash(this.uid,this.upw);
	}
	@Override
	public boolean equals(Object obj) {
	User other = (User)obj;
	return this.uid.equals(other.uid)&&this.upw.equals(other.upw);
	}
	@Override
	public String toString() {
		
		return String.format("%s\t%s", this.uid, this.upw);
				
	}

	public User(String uid, String upw) {
		
		this.uid = uid;
		this.upw = upw;
	}

	public User() {
		
	}
	
	
	
	

}
