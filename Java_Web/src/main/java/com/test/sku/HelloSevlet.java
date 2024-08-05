package com.test.sku;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/hello")
public class HelloSevlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String msg = request.getParameter("dan");
		List<String> list = new ArrayList<>();
		int dan = Integer.parseInt(msg);
		
		for(int i = 1; i<10; i++) {
			list.add(String.format("%d * %d = %d", dan, i, dan*i));
		}
		
		PrintWriter out = response.getWriter();
		for(int i = 0; i<list.size(); i++) {
			out.println(list.get(i) + "<br>");
		}
		out.println("<p>");
		for(int i = 2; i<10; i++) {
			out.print(String.format("<a href='hello?dan=%d'>%d</a> ", i, i));
		}
		out.println("<p>");
		String form  = "<form action='/hello' method = 'post'>";
		form += "단<input type='text' name='dan' value= '2'>";
		form += "<button type='submit'>확인</button>";
		form += "</form>";
		out.println(form);
		
	}
}
