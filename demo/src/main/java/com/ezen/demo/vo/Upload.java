package com.ezen.demo.vo;

import java.sql.Date;
import java.util.*;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Upload {
	private int num;
	private String writer;
	private String comments;
	private Date udate;
	private String fpath;
//	private List<String> fname = new ArrayList<>();	
	private List<Attach> attach = new ArrayList<>();	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getAnum() {
		return num;
	}
	public void setAnum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	

//	public List<String> getFname() {
//		return fname;
//	}
//	public void setFname(List<String> fname) {
//		this.fname = fname;
//	}
//	
	
	public List<Attach> getAttach() {
		return attach;
	}
	public void setAttach(List<Attach> attach) {
		this.attach = attach;
	}
	@Override
	public boolean equals(Object obj) {
		Upload other= (Upload)obj;
		return this.num==other.num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.num);
	}
	@Override
	public String toString() {
		
		return String.format("%d\t%s\t%s", num,writer,udate);
	}
	
	
		
	
	
	

}
