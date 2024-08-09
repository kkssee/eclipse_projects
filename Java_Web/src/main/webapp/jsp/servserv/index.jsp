<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<style type ="text/css">
	img{width : 98%;}
	main div:last-child{display:fle; justify-content : center; margin-top : 4em;}
	li{display: inline-block; width : 5em;}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
function logout() 
{
	
   
    $.ajax( { 
      url : 'user',		
      method: 'post',
      cache: false,
      data:{'cmd':'logout'},			
      dataType:'json',	
      success:function(res){
    	
      		alert(res.logout ? "Logout 성공" : "Logout 실패");
            location.href = "user?cmd=loginForm";
      },
      error:function(xhr,status, err) {
         alert('err: ', err);
      }
    });   
   
 }
</script>
</head>
<body>
<main>
   <nav>			<!-- nav : 기능 없고 div=main과 같음 -> 사람이나 기계에 유리 -->
      <ul>			
         <li><a href="user">HOME</a>
         <li><a href="user?cmd=loginForm">LOGIN</a>
         <li><a href="user?cmd=userList">LIST</a>
         <li><a href="user?cmd=addForm">ADD</a>
         <li><a href="javascript:logout();">LOGOUT</a>
      </ul>					<!-- ul = unordered list (순서가 없으니, 번호가 없음) / 
      							li = list item : 목록을 구성하는 아이템_ ul이라 도트로 찍히고 , ol이면 숫자로 찍힘-->
   </nav>
   <img src = "./img/sea.jpg">			<!-- 현재 디렉토리 "./" -->
</main>
</body>
</html>