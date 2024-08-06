<%@page import="java.util.*"%>
<%@page import="com.test.sku.servlet.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<User> list = (List<User>)request.getAttribute("userList");
	User u = new User();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
</head>
<body>
<div id = "main">
	<input type="hidden" name="cmd" value="userList">
	<p>
<% 
   for(int i=0;i<list.size();i++) { %>
   		<a href="user?cmd=detail&uid=<%=list.get(i).getUid()%>"> <%=list.get(i).getUid()%> </a>
      <br>
<%   }
%>
<p>
</div>
</body>
</html>