package com.test.sku.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserService {
	private HttpServletRequest request;
	private  HttpServletResponse response;

	public UserService() {}
	public UserService(HttpServletRequest request, HttpServletResponse response) 
	{
		this.request = request;
		this.response = response;
	}
	
	public String process() 
	{	//요청이 오면? ID / PW & login form도 요청해야 함(파라미터 중에 cmd 방식 사용-> 요청이 여러개다보니 분류해야함)
		// 요청이 여러가지 -> 출력 화면(view = jsp)도 여러개여야 함
		String cmd = request.getParameter("cmd");
		String viewPath = null;
		
		if(cmd==null)
		{
			return "/jsp/index.jsp";			//index.jsp : 기능을 찾아서 클릭할 수 있게끔 web사이트의 관문에 해당
		}
		
		if(cmd.equals("addUser") || cmd.equals("updatePwd") || cmd.equals("delete"))
		{
			if(!loginCheck())
			{
				Map<String, Object> map = new HashMap<>();
				map.put("added",false);
				map.put("updated",false);
				map.put("deleted",false);
				map.put("cause","로그인 사용자만 이용가능한 기능입니다.");
				map.put("loginRequired",true);
				sendJSON(map);
				return null;
			}
		}
		
		if(cmd ==null || cmd.equals("loginForm"))			// cmd의 value = loginForm 
		{
			viewPath = "/jsp/loginForm.jsp";
		} else if(cmd.equals("login")) {
			// login 버튼 누르면 cmd가 로그인이 되도록 -> 로그인 절차를 아래 코드로 작성
			// 로그인 절차 수행_id/pwd 받아오기	-> 이걸 인스턴스(User)에 담아서 DB(DAO)로 전달한다
			//-> login 결과가 t/f로 ok로 이용자 화면에 출력(=jsp가 있어야 한다) => 이걸 전달하기 위해서 request에 담아줌
			
			String uid =request.getParameter("uid");
			String pwd =request.getParameter("pwd");
			// Document object Model=DOM) : document.querySelector("#uid") : uid는 label의 uid
			//결국 input box를 오브젝트로 표현한 것 /#__ : 이걸 selector을 의미 
			
			User user = new User(uid,pwd);			//user class & user 테이블 필요
			UserDAO dao = new UserDAO();			//UserDAO class 필요
			boolean ok = dao.login(user);			//DAO에 login method 필요
			
			// 로그인 통과한 이용자는 어떤 페이지로 이동하든지 로그인 여부를 확인할 수 있도록
			// 영역 object에 로그인 성공 사실을 memory에 기억해놓는다
						
			// 1. 세션영역 구하기request.getSession().setAttribute()_ 영역 obj의 특징 :  get/setAttribute()사용가능
			if(ok) 
			{
				request.getSession().setAttribute("uid", uid);;		// 이 한 줄이 login 인증을 해주는 것
			}
			
			// 2. 이용자에게 로그인 성공/실패 여부를 전달
			sendJSON("ok", ok +"");
			
			/*
			request.setAttribute("login", ok);		// request 영역에서 공유되는 object = request object 
			viewPath = "/jsp/loginResult.jsp";		// loginResult.jsp 생성	
			*/
			// ajax로 위 메세지 바꿀 것

		} else if(cmd.equals("logout")){
			request.getSession().invalidate();			//	invalidate() :  앞의 request.getSession()을 무효화 하는 것
			sendJSON("logout", true+"");
			return null;
		} else if(cmd.equals("userList"))
		{
			User user = new User();			//user class & user 테이블 필요
			UserDAO dao = new UserDAO();			//UserDAO class 필요
		
			List<User> list  = dao.getList();
		
			request.setAttribute("userList", list);		// user목록 가져올때는 userList.jsp니까 userList로 & 경로도 바꿔줘야 함(viewPath)
			viewPath = "/jsp/userList.jsp";
		}else if(cmd.equals("detail"))		// 검색시 &uid=smith, 이렇게 해야 함
		{
			String uid =request.getParameter("uid");
			UserDAO  dao = new UserDAO();
			User detail = dao.getDetail(uid);
			request.setAttribute("detail", detail);
			viewPath = "/jsp/detailUser.jsp";
		}else if(cmd.equals("editPwd")) 
		{
	         String uid = request.getParameter("uid");
	         request.setAttribute("uid", uid);
	         viewPath = "/jsp/editPwd.jsp";
	         
	      }else if(cmd.equals("updatePwd")) 
	      {
	         String uid = request.getParameter("uid");
	         String newPwd = request.getParameter("pwd");
	         UserDAO dao = new UserDAO();
	         boolean updated = dao.updatePwd(new User(uid, newPwd));
	      	 sendJSON("updated", updated +"");
	      }
	      else if(cmd.equals("delete"))
	      {
	    	  String uid = request.getParameter("uid");
		      UserDAO dao = new UserDAO();
		      User u = new User();
		      u.setUid(uid);
		      boolean deleted = dao.deleteUser(u);
	         sendJSON("deleted", deleted +"");
	      }else if(cmd.equals("addForm"))
	      {
	    	viewPath = "/jsp/userInput.jsp";
	      }
	      else if(cmd.equals("addUser"))
	      {
	    	String uid = request.getParameter("uid");
		    String pwd = request.getParameter("pwd");
		    UserDAO dao = new UserDAO();
		    User user = new User(uid, pwd);
		    	 
	        boolean added = dao.addUser(user);
	      	sendJSON("added", added +"");
		  }
		return viewPath;
	}
	// 공통인 부분은 method로 만들어서 관리가 용이하게끔 변경
		private void sendJSON(String key,String value )
		{
			String json = String.format("{\"%s\" : %s}", key, value);
			PrintWriter out = null;
			try 
			{
				out = response.getWriter();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.print(json); 
			out.flush();
		}
		
		// addUser에서 if(!loginCheck())뒤에 반복되는 부분을 method로 만듦
		private void sendJSON(Map<String, Object> map )
		{
			JSONObject jsObj = new JSONObject(map);
			String js =jsObj.toJSONString();
			try {
				PrintWriter out = response.getWriter();
				out.print(js);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// login 검사하는 기능을 method로 만들기
		private boolean loginCheck()
		{
			Object obj = request.getSession().getAttribute("uid");		//uid가 있으면 로그인한 사람
			return obj != null;			// login 했으면 true, 안했으면 false가 return
		}

		
		
		/*
		  addUser에 반복되던 걸 objcet로 만들 것 -> sendJSON<Map<>)에 있음
			//login 한 사람만 쓸 수 있게 check
	    	if(!loginCheck()) {
	    		
	    		JSONObject jsObj = new JSONObject();
	    		jsObj.put("added",false);
	    		jsObj.put("cause","로그인 이용자만 사용할 수 있습니다.");
	    		jsObj.put("loginRequired",true);
	    		String js =jsObj.toJSONString();
	    		try {
					PrintWriter out = response.getWriter();
					out.print(js);
					out.flush();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
		 */
		
	}
