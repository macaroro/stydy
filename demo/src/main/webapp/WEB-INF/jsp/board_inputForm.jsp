
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판</title>
<style type="text/css">
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:10px auto; }
	label { display:inline-block; width:3em; padding-right:0.5em; text-align: right;}
	label[for=contents]{vertical-align:top; margin-top:2em; }
	#div_btn {width:fit-content; margin:10px auto; }
	input[type=text] { width:300px;}
	textarea { width:300px; }
	footer { width:fit-content; margin:5px auto; }
	a { text-decoration: none;}
	#title {margin-bottom:0.5em;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
function add()
{
	var serData = $('#add_form').serialize();
	$.ajax({
		url:'/bbs/save',
		method:'post',
		cache:false,
		data:serData,
		dataType:'json',
		success:function(res){
		if(res.saved){
			alert('저장성공')
		}else if(res.saved==false){
			alert('로그인후 이용가능합니다.')
		}
		
		
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
<main>
<h3>게시판 글쓰기 폼</h3>
<form id="add_form" onsubmit="return add();"> 
	<input type="hidden" name="pcode" value="0">
	<div><label for="title">제목</label>
		<input type="text" id="title" name="title" placeholder="제목을 입력하세요">
	</div>
	
	<div><label for="contents">글내용</label>
		<textarea rows="5" cols="30" id="contents" name="contents" placeholder="글을 입력하세요"></textarea>
	</div>

	

	<div id="div_btn">
		<button type="reset">취소</button>
		<button type="submit">저장</button>
	</div>
</form>

</main>
</body>
</html>