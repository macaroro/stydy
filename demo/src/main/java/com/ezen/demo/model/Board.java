package com.ezen.demo.model;

import java.sql.Date;
import java.util.Objects;


public class Board{
	private int num;
	private String title;
	private String contents;
	private String author;
	private Date wdate;
	private int pcode;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	@Override
	public boolean equals(Object obj) {
		Board board = (Board)obj;
		return this.num==board.num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.num);
	}
//	@Override
//	public String toString() {
//		return String.format("%d\t%s\t%d\t%d\t%s\t%s\t%d\t%d", empno,ename,deptno,sal,hiredate,job,mgr,comm);
//	}
	public Board(int num) {
	
		this.num = num;
	}
	public Board() {}
	
	

}
