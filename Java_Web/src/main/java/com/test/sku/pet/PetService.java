package com.test.sku.pet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.test.sku.servlet.User;
import com.test.sku.servlet.UserDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetService {
	private HttpServletRequest request;
	private  HttpServletResponse response;
	
	public PetService() { }
	public PetService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public String process() {
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(cmd==null) {
			return "/jsp/pet/pet_index.jsp";
		} else if(cmd.equals("petList")) {
			PetDAO dao = new PetDAO();
			List<PetVO> list  = dao.getList();
		
			request.setAttribute("petList", list);	
			viewPath = "/jsp/pet/petList.jsp";
		} else if(cmd.equals("petInput")) {
			viewPath = "/jsp/pet/petInput.jsp";
		} else if(cmd.equals("addPet"))
	      {
			int price = Integer.parseInt(request.getParameter("price"));
	    	String name = request.getParameter("name");
	    	String origin = request.getParameter("origin");
			float weight = Float.parseFloat(request.getParameter("weight"));
			Date birth = Date.valueOf(request.getParameter("birth"));
		    String pic = request.getParameter("pic");
		    PetDAO dao = new PetDAO();
		    PetVO p = new PetVO(price, weight, birth, name, origin, pic);
	        boolean added = dao.addPet(p);
	      	sendJSON("added", added + "");
		} else if (cmd.equals("petDetail")) {
			/*String category = request.getParameter("cat");
			String key = request.getParameter("key");
			System.out.print(category);
			PetDAO dao = new PetDAO();
			PetVO detail = dao.getDetail(category, key);
			request.setAttribute("detail", detail);
			viewPath = "/jsp/pet/petDetail.jsp";*/
			
			int no = Integer.parseInt(request.getParameter("no"));
			PetDAO dao = new PetDAO();
			PetVO detail = dao.getDetail(no);
			request.setAttribute("detail", detail);
			viewPath = "/jsp/pet/petDetail.jsp";
		} else if(cmd.equals("delete")) {
	    	  int no = Integer.parseInt(request.getParameter("no"));
		      PetDAO dao = new PetDAO();
		      PetVO p = new PetVO();
		      p.setNo(no);
		      boolean deleted = dao.deletePet(p);
	         sendJSON("deleted", deleted +"");
	      } else if(cmd.equals("petEdit")) {
	         int no = Integer.parseInt(request.getParameter("no"));
	         request.setAttribute("no", no);
	         viewPath = "/jsp/pet/petEdit.jsp";
	      } else if(cmd.equals("petUpdate")) {
	    	 int no = Integer.parseInt(request.getParameter("no"));
	    	 float weight = Float.parseFloat(request.getParameter("weight"));
	    	 int price = Integer.parseInt(request.getParameter("price"));
	    	 String pic = request.getParameter("pic");
	    	 
	    	 PetDAO dao = new PetDAO();
	         boolean updated = dao.updatePet(new PetVO(no, price, weight, pic));
	      	 sendJSON("updated", updated +"");
	      }
	      else if (cmd.equals("search")) {
				String name = request.getParameter("name");
				PetDAO dao = new PetDAO();
				PetVO detail = dao.getDetail(name);
				request.setAttribute("detail", detail);
				viewPath = "/jsp/pet/petDetail.jsp";
			}
		return viewPath;
	}
	
	private void sendJSON(String key, String value)
	{
		String json = String.format("{\"%s\": %s}", key, value);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
		    out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
