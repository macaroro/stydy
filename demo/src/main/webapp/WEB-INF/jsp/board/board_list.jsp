<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글목록</title>
<style type="text/css">
h2{ text-align: center;}
table{   width: 50%; margin: 0 auto;

    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
    padding: 10px;
   
  }
 tr:nth-child(2n) {
    background-color: #bbdefb;
  }
 tr:nth-child(2n+1) {
    background-color: #e3f2fd;
  }
  th{ background-color: lightgreen;}
  tr:hover{background-color: orange;}
</style>
</head>
<body>
<h2>글목록</h2>
<table>
<tr><th>글번호</th><th>제목</th><th>내용</th><th>작성자</th><th>날짜</th><th>부모글</th></tr>

<c:forEach var="bbs" items="${list}">
<tr>
 <td>${bbs.num}</td>
<td><a href="/bbs/detail/${bbs.num}">${bbs.title}</a></td>
<td>${bbs.contents}</td>
<td>${bbs.author}</td>
<td>${bbs.wdate}</td>
<td>${bbs.pcode}</td>
</tr>
</c:forEach>
</table>

<form action="/bbs/search" method="post">

<select name="category">
<option>title</option>
<option>author</option>

</select>

검색창 <input type="text" name="key" list="category" id="key">

<button type="submit">검색</button>
</form>
</body>
</html>