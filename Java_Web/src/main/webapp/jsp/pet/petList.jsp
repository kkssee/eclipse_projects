<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<PetVO> list = (List<PetVO>)request.getAttribute("petList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>펫 목록</title>
</head>
<body>
<h3>Pet List</h3>
<div id = "main">
<input type="hidden" name="cmd" value="petList">
<table>
<tr><th>번호</th><th>이름</th><th>원산지</th><th>무게</th><th>생일</th><th>가격</th></tr>
<% 
   for(int i=0;i<list.size();i++) { 
		PetVO p = list.get(i); %>
   <tr>
   		<td><a href="pet?cmd=petDetail&no=<%=list.get(i).getNo()%>"><%=p.getNo()%> </a> </td>
   		 <td><a href="pet?cmd=petDetail&no=<%=list.get(i).getNo()%>"><%=p.getName() %></a> </td>
   		 <td> <%=p.getOrigin()%> </td>
   		 <td> <%=p.getWeight()%> </td>
   		 <td> <%=p.getBirth()%> </td>
   		 <td> <%=p.getPrice()%> </td>
     </tr>
<% }
%>
</table>
<a href="pet?cmd=addForm"><button>펫 추가</button></a>
</div>
</body>
</html>