package com.test.sku.servlet;

import java.sql.*;
import java.util.*;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	static Scanner kbd = new Scanner(System.in);

	private Connection getConn()
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			conn =  DriverManager.getConnection(
		            "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		} catch(Exception e)
		{
			e.printStackTrace();
		} return null;
	}
	
	public UserDAO() { }
	public boolean login(User user) {
		String sql = "SELECT * FROM users WHERE userid = ? AND userpwd = ?";
		conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, user.getUid());
	         pstmt.setString(2, user.getPwd());
	         rs = pstmt.executeQuery();
	         if(rs.next()) {
	        	return true;
	         }
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      } finally {
	    	  closeAll();
	      } return false;
	}
	public List<User> getList() {
		String sql = "SELECT * FROM users";
		conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         List<User> list = new ArrayList<>();
	         
	         while(rs.next()) {
	        	String uid = rs.getString("USERID");
	        	String pwd = rs.getString("USERPWD");
	        	User user = new User();
	        	user.setUid(uid);
	        	user.setPwd(pwd);
	        	list.add(user);
	         } return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      } finally {
	    	  closeAll();
	      } return null;
	}
	public User detail(String uid) {
		List<User> list = getList();
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getUid().equals(uid)) {
				return list.get(i);
			}
		}
		return null;
	}

	private void closeAll() {
		try {
			if(rs != null)		rs.close();
			if(pstmt != null)	pstmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
