<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	boolean ok = (Boolean)request.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login Result</title>
<script type="text/javascript">
	var loginOk = <%=ok%>; 
	var msg = loginOk?"login success":"login failed";
	alert(msg);
</script>
</head>
<body>

</body>
</html>