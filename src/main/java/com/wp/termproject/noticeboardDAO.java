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

public class noticeboardDAO {
	private DBConnectionInfo dbInfo;
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public noticeboardDAO(DBConnectionInfo dbInfo) {
		super();
		this.dbInfo = dbInfo;
	}
	
	protected void connect() throws ClassNotFoundException, SQLException{ //DB connect
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
	
	protected void disconnect() throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(stmt != null){
			stmt.close();
		}
		if(conn != null){
			conn.close();
		}
	}
	
	public List<NoticeDO> selectAllboards() {
		List<NoticeDO> notice_list = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = "select ROW_NUMBER() OVER(ORDER BY a.day desc) row_num,a.* from noticeboard_list a ORDER BY a.day desc";
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					notice_list = new ArrayList<NoticeDO>();
					
					while (rs.next()) {
						NoticeDO board = new NoticeDO();
						board.setRownum(rs.getInt("row_num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setDay(rs.getString("day"));
						
						notice_list.add(board);
					}
				}		
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice_list;
	}
	public List<NoticeDO> selectAllTitle(String title) {
		List<NoticeDO> notice_list = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select rownum,a.* from noticeboard_list a where title like '%%%s%%'",title);
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					notice_list = new ArrayList<NoticeDO>();
					
					while (rs.next()) {
						NoticeDO board = new NoticeDO();
						board.setRownum(rs.getInt("rownum"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setDay(rs.getString("day"));
						
						notice_list.add(board);
					}
				}		
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice_list;
	}
	
	public List<NoticeDO> selectAllContent(String content) {
		List<NoticeDO> notice_list = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select rownum,a.* from noticeboard_list a where content like '%%%s%%'",content);
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					notice_list = new ArrayList<NoticeDO>();
					
					while (rs.next()) {
						NoticeDO board = new NoticeDO();
						board.setRownum(rs.getInt("rownum"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setDay(rs.getString("day"));
						
						notice_list.add(board);
					}
				}		
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice_list;
	}
	
	public List<NoticeDO> selectAllId(String id) {
		List<NoticeDO> notice_list = null;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("select rownum,a.* from noticeboard_list a where id = '%s'",id);
				rs = stmt.executeQuery(sql);
				
				if(rs.isBeforeFirst()){
					notice_list = new ArrayList<NoticeDO>();
					
					while (rs.next()) {
						NoticeDO board = new NoticeDO();
						board.setRownum(rs.getInt("rownum"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setDay(rs.getString("day"));
						
						notice_list.add(board);
					}
				}		
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice_list;
	}

	public NoticeDO selectboard(NoticeDO id)
	{
		NoticeDO board = null;
		
		try {
			connect();
			
			if(conn != null)
			{
				stmt = conn.createStatement();
				
				String sql = String.format("select * from noticeboard_list where id ='%s' and day = to_date('%s','YYYY-MM-DD HH24:MI:SS')", id.getId(), id.getDay());
				rs = stmt.executeQuery(sql);
				
				if (rs.isBeforeFirst())
				{
					rs.next();
					
					board = new NoticeDO();
					board.setTitle(rs.getString("title"));
					board.setContent(rs.getString("content"));
					board.setId(rs.getString("id"));
					board.setDay(rs.getString("day"));
				}
			}			
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
	}
	
	
	public int insertboards(NoticeDO boards) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("insert into noticeboard_list(title,content,id,day) values ('%s', '%s','%s',%s)", boards.getTitle(),boards.getContent(),boards.getId(),boards.getDay());
				result = stmt.executeUpdate(sql);
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateboards(NoticeDO boards) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("update noticeboard_list set title='%s', content='%s', day = to_date('%s','YYYY-MM-DD HH24:MI:SS') where id ='%s' and day = to_date('%s','YYYY-MM-DD HH24:MI:SS')",
						boards.getTitle(),boards.getContent(), boards.getUpdateDay(), boards.getId(), boards.getDay());
				result = stmt.executeUpdate(sql);
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteboards(NoticeDO board) {
		int result = -1;
		
		try {
			connect();
			
			if(conn != null){
				stmt = conn.createStatement();
				
				String sql = String.format("delete from noticeboard_list where id ='%s' and day = to_date('%s','YYYY-MM-DD HH24:MI:SS')",board.getId(),board.getDay());
				result = stmt.executeUpdate(sql);
			}
			// DB operations
			
			disconnect();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}