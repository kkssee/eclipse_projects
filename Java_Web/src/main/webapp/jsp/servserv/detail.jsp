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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
function goList() {
	location.href ="user?cmd=userList";
}
 
 function deleteUser() {
	 if(!confirm('ㄹㅇ삭제?')) return;
	 let obj = {};	
	 obj.cmd = 'delete';
	 obj.uid = '<%=u.getUid()%>';
	 
	 $.ajax({ 
		url : 'user',
		method: 'post',
		cache: false,
		data:obj,
		dataType:'json',
		success:function(res){
			alert(res.deleted?'delete success':'delete failed');
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
<h3>User Detail</h3>
<div id = "main">
	<input type="hidden" name="cmd" value="detail">
	<table>
  	 <tr><th>아이디</th><th><%=u.getUid()%></th></tr>
  	 <tr><th>암 호</th><th><%=u.getPwd()%></th></tr>
	</table>

	<a href="user?cmd=userList">userList</a>
	<a href="user?cmd=userList"><button>userList</button></a>
	<a href="user?cmd=userList"><button onclick="goList();">userList</button></a>
	<br>
   	<a href="user?cmd=editPwd&uid=<%=u.getUid()%>"><button>update password</button></a>
   	<a href="javascript:deleteUser();">delete user</a>
</div>
</body>
</html>