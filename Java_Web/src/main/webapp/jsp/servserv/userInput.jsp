<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addUser</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
    console.log('jQuery ready');
 });
 
 function userInput() {
	 if(!confirm('사용자 정보를 추가하시겠습니까?')) return;
	 let cmd = $('#cmd').val();	// document.querySelector('#cmd').value
	 let uid = $('#uid').val();	
	 let pwd = $('#pwd').val();	
	 
	 let obj = {};	// JSON 표현식
	 obj.cmd = cmd;
	 obj.uid = uid;
	 obj.pwd = pwd;
	 
	 $.ajax({ 
			url : 'user',
			method: 'post',
			cache: false,
			data:obj,
			dataType:'json',
			success:function(res){
				alert(res.added?'add success':'add failed');
				if(res.added) {
					alert(res.cause);
					location.href = "user?cmd=detail&uid=" + uid;
				}
			},
			error:function(xhr, status, err) {
				alert('err: ' + err);
			}
	});
 }
</script>


</head>
<body>
<h3>sign in form</h3>
	<form action="user" method="post">
		<input type="hidden" name="cmd" id="cmd" value="addUser">
		<div><label for="uid">userid</label>
			<input type="text" name="uid" id="uid">
		</div>
		<div><label for="pwd">password</label>
			<input type="password" name="pwd" id="pwd">
		</div>
		<div>
			<button type="reset">취소</button>
			<a href="javascript:userInput();">sign in</a>
		</div>
	</form>
</body>
</html>