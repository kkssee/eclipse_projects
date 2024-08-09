package com.test.sku.pet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.sku.servlet.User;

public class PetDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Connection getConn() {
		try {
	    	Class.forName("oracle.jdbc.OracleDriver");
	    	conn = DriverManager.getConnection(
	    			"jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
	     	return conn;
	   	} catch(Exception e) {
	   		e.printStackTrace();
	  	} return null; 
	}
	
	 public boolean addPet(PetVO p) {
		   String sql = "INSERT INTO pets (no, name, origin, weight, birth, price, pic) \r\n"
		   		+ "	   VALUES(pets_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		      conn = getConn();
		      try {
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, p.getName());
		         pstmt.setString(2, p.getOrigin());
		         pstmt.setFloat(3, p.getWeight());
		         pstmt.setDate(4, p.getBirth());
		         pstmt.setInt(5, p.getPrice());
		         pstmt.setString(6, p.getPic());
		         
		         int rows = pstmt.executeUpdate();
		         return rows>0;
		      }catch(SQLException sqle) {
		         sqle.printStackTrace();
		      }finally {
		         closeAll();
		      } return false;
		}
	
	public List<PetVO> getList() {
		String sql = "SELECT * FROM pets ORDER BY no";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<PetVO> list = new ArrayList<>();
			while (rs.next()) {
				int no = rs.getInt("NO");
				int price = rs.getInt("PRICE");
				float weight = rs.getFloat("WEIGHT");
				Date birth = rs.getDate("BIRTH");
				String name = rs.getString("NAME");
				String origin = rs.getString("ORIGIN");
				String pic = rs.getString("PIC");
				PetVO p = new PetVO(price, weight, birth, name, origin, pic);
				p.setNo(no);
				list.add(p);
			} return list;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeAll();
		} return null;
	}
	
	public PetVO getDetail(int no) {
	      String sql = "SELECT * FROM pets WHERE no=?";
	      conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	int dno = rs.getInt("NO");
				int price = rs.getInt("PRICE");
				float weight = rs.getFloat("WEIGHT");
				Date birth = rs.getDate("BIRTH");
				String name = rs.getString("NAME");
				String origin = rs.getString("ORIGIN");
				String pic = rs.getString("PIC");
				PetVO p = new PetVO(price, weight, birth, name, origin, pic);
				p.setNo(dno);
				
	            return p;
	         }
	      } catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      } return null;
	   }
	
	public boolean deletePet(PetVO p) {
		String sql = "DELETE FROM pets WHERE no = ?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getNo());
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeAll();
		} return false;
	}
	
	public boolean updatePet(PetVO p) {
		String sql = "UPDATE pets SET weight = ?, price = ?, pic = ? WHERE no = ?";
		conn = getConn();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, p.getWeight());
			pstmt.setInt(2, p.getPrice());
			pstmt.setString(3, p.getPic());
			pstmt.setInt(4, p.getNo());

			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeAll();
		} return false;
	}
	
	public PetVO getDetail(String dname) {
	      String sql = "SELECT * FROM pets WHERE name = ?";
	      conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, dname);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	int dno = rs.getInt("NO");
				int price = rs.getInt("PRICE");
				float weight = rs.getFloat("WEIGHT");
				Date birth = rs.getDate("BIRTH");
				String name = rs.getString("NAME");
				String origin = rs.getString("ORIGIN");
				String pic = rs.getString("PIC");
				PetVO p = new PetVO(price, weight, birth, name, origin, pic);
				p.setNo(dno);
	            return p;
	         }
	      } catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      } return null;
	   }

	private void closeAll() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}