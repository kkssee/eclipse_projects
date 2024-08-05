package com.test.sku.jdbc;

import java.sql.*;
import java.util.*;

public class BoardDAO {
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
		}catch(Exception e)
		{
			e.printStackTrace();
		}return null;
	}
	
	private int bid;
	private String title;
	private int attcnt;
	
	public BoardDAO() { }
	
	public List<Map<String, String>> getBoards()
	   {
	      String sql = "SELECT bid,title, "
	            + "( "
	            + "    SELECT COUNT(*) FROM attach a WHERE a.bid=b.bid "
	            + ")attcnt FROM board b";
	      conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         List<Map<String, String>> list = new ArrayList<>();
	         
	         while(rs.next()) {
	            int bid = rs.getInt("BID");
	            String title = rs.getString("TITLE");
	            int attcnt = rs.getInt("ATTCNT");
	            Map<String,String> map = new HashMap<>();
	            map.put("BID", bid+"");
	            map.put("TITLE", title);
	            map.put("ATTCNT", attcnt+"");
	            list.add(map);
	         } return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      } return null;
	   }
	
}
