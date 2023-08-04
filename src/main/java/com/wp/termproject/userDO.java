package com.wp.termproject;

public class userDO {
	private String id;
	private String name;
	private String password;
	private String permission;
	private String modId;
	
	public userDO() {
		// TODO Auto-generated constructor stub
	}

	public userDO(String id, String name, String password, String permission, String modId) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.permission = permission;
		this.modId = modId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}
	
	
	
}
