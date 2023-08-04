package com.wp.termproject;

public class NoticeDO {
	private int rownum;
	private String title;
	private String content;
	private String id;
	private String day;
	private String updateDay;
	
	public NoticeDO() {
		// TODO Auto-generated constructor stub
	}

	public NoticeDO(int rownum, String title, String content, String id, String day, String updateDay) {
		super();
		this.rownum = rownum;
		this.title = title;
		this.content = content;
		this.id = id;
		this.day = day;
		this.updateDay = updateDay;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(String updateDay) {
		this.updateDay = updateDay;
	}
	
	

}
