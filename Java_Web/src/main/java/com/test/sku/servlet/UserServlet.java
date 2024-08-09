package com.test.sku.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet 
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// servlet: web브라우저 요청을 받아서 실행될 수 있는 java 클래스_http 요청을 받아서 서버에서 실행되는 서버측 java 클래스
		// service : 요청이 오면 무조건 돌아감 -> 요청이 오면 tomcat이 받아서 service 메소드를 실행시킴
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewPath = new UserService(request, response).process();			// req, res를 UserService로 보내주는 것 -> 이렇게 만들어놓으면 손대지 않아도 됨
		
		if(viewPath != null)	
		{
			// 이걸 화면에 출력 -> jsp를 보여주면 됨
			getServletContext().getRequestDispatcher(viewPath).forward(request, response);
											//  viewPath:	jsp		jsp가 돌아가면서 request에 접근	-> jsp에게 출력하라고 servlet이 시키는 것
		}
		
	}

}
