<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.test.sku.pet.PetVO"%>
<%
	PetVO p = (PetVO)request.getAttribute("detail");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>유저상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
function goList() {
	location.href ="pet?cmd=petList";
}
 
 function deletePet() {
	 if(!confirm('ㄹㅇ삭제?')) return;
	 let obj = {};	
	 obj.cmd = 'delete';
	 obj.no = '<%=p.getNo()%>';
	 
	 $.ajax({ 
		url : 'pet',
		method: 'post',
		cache: false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.deleted?'delete success':'delete failed');
			if (res.deleted) {
				location.href = "pet";
			}
		},
		error:function(xhr, status, err) {
			alert('err: ' + err);
		}
	 });
 }
</script>
<style type="text/css">
   table {border:1px solid black; padding:10px; border-collapse:collapse; 
      border-spacing: 0; margin:0.2em auto;
   }
   th:first-child { background-color:#ddd; border-right:3px double black;}
   th,td {border:1px solid black; padding: 0.2em 1em;}
   td>a { text-decoration:none; color:blue; }
   main {width:fit-content; margin:1em auto;}
   h3 {text-align:center; text-decoration: underline;}
</style>

</head>

<body>
<h3>Pet Detail</h3>
<div id = "main">
	<input type="hidden" name="cmd" id="cmd" value="petDetail">
	<img src = "./img/pet/<%=p.getPic()%>" width="200" height="250">	
	<table>
	<tr><th>번호</th><th>이름</th><th>원산지</th><th>무게</th><th>생일</th><th>가격</th></tr>
  	<tr> <td> <%=p.getNo()%></td>
   		 <td> <%=p.getName() %> </td>
   		 <td> <%=p.getOrigin()%> </td>
   		 <td> <%=p.getWeight()%> </td>
   		 <td> <%=p.getBirth()%> </td>
   		 <td> <%=p.getPrice()%> </td> </tr>
	</table>

	<a href="pet?cmd=petList"><button onclick="goList();">petList</button></a>
	<br>
   	<a href="pet?cmd=petEdit&no=<%=p.getNo()%>"><button>update password</button></a> 
   	<a href="javascript:deletePet();">delete</a>
</div>
</body>
</html>