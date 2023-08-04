package com.wp.termproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class userinfoDAO {
	private DBConnectionInfo dbInfo;
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public userinfoDAO(DBConnectionInfo dbInfo) {
		super();
		this.dbInfo = dbInfo;
	}
	
	protected void connect() throws SQLException, ClassNotFoundException
	{
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/oracle_xe");
			
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void disconnect() throws SQLException
	{
		// disconnect from DB server
		if(rs != null)
		{
			rs.close();
		}
		if(stmt != null)
		{
			stmt.close();
		}
		if(conn != null)
		{
			conn.close();
		}
	}
	
	public List<userDO> selectAllUser()
	{
		List<userDO> userList = null;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = "select * from BOARD_USER";
				rs = stmt.executeQuery(sql);
				
				if (rs.isBeforeFirst())
				{
					userList = new ArrayList<userDO>();
					
					while (rs.next())
					{
						userDO user = new userDO();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						user.setPermission(rs.getString("permission"));
						
						userList.add(user);
					}
				}
			}			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}
	public userDO selectUser(String id)
	{
		userDO user = null;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = String.format("select * from BOARD_USER where id ='%s'", id);
				rs = stmt.executeQuery(sql);
				
				if (rs.isBeforeFirst())
				{
					rs.next();
					
					user = new userDO();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setPermission(rs.getString("permission"));
				}
			}			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public int insertUser(userDO user)
	{
		int result = -1;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = String.format("insert into board_user values ('%s','%s','%s','%s')", user.getId(), user.getName(), user.getPassword(), user.getPermission());
				result = stmt.executeUpdate(sql);
			}			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateUser(userDO user)
	{
		int result = -1;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = String.format("update board_user set name = '%s', password = '%s', id='%s' where id = '%s'", user.getName(), user.getPassword(), user.getModId(), user.getId());
				result = stmt.executeUpdate(sql);
			}			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deletUser(String id)
	{
		int result = -1;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = String.format("delete from board_user where id = '%s'", id);
				result = stmt.executeUpdate(sql);
			}
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
