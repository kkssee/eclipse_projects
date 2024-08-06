<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<style type="text/css">
	#main { width:fit-content; margin:0.5em auto; padding:1em; }
	form { border:1px solid black; text-align:center; padding:0.5em; }
	h3 { text-align:center; }
	div:last-child { margin-top:0.3em; text-align:center; }
	label { display:inline-black; width:3em; }
</style>
<script type="text/javascript">
	function formCheck() {
		var uid = document.querySelector("#uid").value;
		var pwd = document.querySelector("#pwd").value;
		if(uid=="" || pwd=="") {
			alert('아이디, 암호를 확인해주세요');
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div id = "main">
	<h3>Login</h3>
	<form action="user" method="post" onsubmit="return formCheck();">
		<input type="hidden" name="cmd" value="login">
		<div><label for="uid">아이디</label>
			<input type="text" name="uid" id="uid">
		</div>
		<div><label for="pwd">암호</label>
			<input type="password" name="pwd" id="pwd">
		</div>
		<div>
			<button type="reset">취소</button>
			<button type="submit">로그인</button>
		</div>
	</form>
</div>


</body>
</html>