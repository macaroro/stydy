<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>자료실 목록보기</title>
<style>
	main { width:fit-content; margin:0 auto; }
	main>h3 { width:fit-content; margin:0.5em auto; }
	table { border:1px solid black; border-spacing:0; border-collapse:collapse; 
		padding:1em; margin:0 auto; width:fit-content;
	}
	th,td { padding:0.3em 1em; border:1px solid black;}
	th { background-color:#add;}
	a { text-decoration:none; }
</style>
</head>
<body>
<main>
<h3>자료실 목록보기</h3>
<table>
<tr><th>번호</th><th>작성자</th><th>작성일</th><th>설명</th><th>첨부</th></tr>
<c:forEach var="vo" items="${list}">
	<tr>
		<td>
			<a href="/files/detail/${vo.num}">${vo.num}</a>
		</td>
		<td>${vo.writer}</td>
		<td>${vo.udate}</td>
		<td>${vo.comments}</td>
		<td> 
			<c:forEach var="aVO" items="${vo.attach}">
				<a href="/files/download/${aVO.num}">${aVO.fname}</a>
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</table>
</main>
</body>
</html>