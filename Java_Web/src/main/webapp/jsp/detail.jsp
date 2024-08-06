<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.test.sku.servlet.User"%>
<%
	User u = (User)request.getAttribute("detail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저상세</title>
</head>
<body>
<div id = "main">
	<input type="hidden" name="cmd" value="detail">
	<p>
	<%=u.getUid()%> <br>
	<%=u.getPwd()%>
<p>
</div>
</body>
</html>