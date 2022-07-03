<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="b" items="${pageInfo.list}">
 ${b.num} 
 ${b.title} 
 ${b.author}
</c:forEach>

<c:forEach var="i" items="${pageInfo.navigatepageNums}">
<c:choose>
<c:when test="${i==pageInfo.pageNum}">
[${i}]
</c:when>
<c:otherwise>

[<a href="/bbs/page?page=${i}">${i}</a>]
</c:otherwise>

</c:choose>
</c:forEach>

</body>
</html>