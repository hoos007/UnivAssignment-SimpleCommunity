<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 시스템 메시지</title>
<style>
	body{
		text-align: center;
	}
</style>
</head>
<body>
	<h1>게시판 시스템 메시지</h1><hr><br>
	<p>${msg}</p>
	<c:if test="${type == 'regi'}">
	<button onclick="location='login.jsp'"/>로그인 화면으로</button>
	</c:if>
	<c:if test="${type == 'del'}">
	<button onclick="location.href='${pageContext.request.contextPath}/userlist/main'"/>메인 화면으로</button>
	</c:if>
	<c:if test="${type == 'list'}">
	<button onclick="location.href='${pageContext.request.contextPath}/userlist/list'"/>사용자 관리 화면으로</button>
	</c:if>
</body>
</html>