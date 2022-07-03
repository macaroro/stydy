<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function login()
{
	var serData = $('#login').serialize();
	$.ajax({
		url:'/user/confirm',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
			alert(res.login? '로그인 성공':'로그인 실패');
		      location.href="/bbs/input"
		},
		error:function(xhr,status,err){
			alert(err);
		}
	});
	return false;
}

function logout()
{
	$.ajax({
		url:'/user/logout',
		method:'post',
		cache:false,
		dataType:'json',
		success:function(res){
			alert(res.logout? '로그아웃 성공':'로그아웃 실패');
		      location.href="/user/login"
		},
		error:function(xhr,status,err){
			alert(err);
		}
	});
	return false;
}

</script>

</head>
<body>
<h2>로그인</h2>
<form id="login" onsubmit="return login();">
아이디<input type="text" name="uid" value="user1">
비밀번호<input type="text" name="upw" value="1111">

<button type="submit">로그인</button>
<button type="reset">취소</button>

</form>

<a href="javascript:logout()">로그아웃</a>
</body>
</html>