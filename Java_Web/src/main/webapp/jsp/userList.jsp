<%@page import="java.util.*"%>
<%@page import="com.test.sku.servlet.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<User> list = (List<User>)request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<style type="text/css">
	table { border:1px solid black; padding:10px; border-collapse:collapse; margin-left:auto;margin-right:auto;}
	tr:first-child>th { background-color:#ddd; border-bottom:3px double black; }
	th,td { border:1px solid black; padding:0.2em 1em;}
	td>a { text-decoration:none; color:blue; }
	tr:nth-child(even) { background-color:#cde; }
	div#main { width:fit-content; margin: 1em auto; }
	h3 {text-align:center; text-decoration: underline;}
</style>
</head>
<body>
<h3>User List</h3>
<div id = "main">
<input type="hidden" name="cmd" value="userList">
<table>
<tr><th>userId</th><th>password</th></tr>
<% 
   for(int i=0;i<list.size();i++) { 
   User u = list.get(i); %>
   <tr>
   		<td><a href="user?cmd=detail&uid=<%=list.get(i).getUid()%>">
   		 <%=u.getUid()%> </a></td>
   		 <td> <%=u.getPwd()%> </td>
     </tr>
<%   }
%>
</table>
<a href="user?cmd=addForm"><button>add user</button></a>
</div>
</body>
</html>