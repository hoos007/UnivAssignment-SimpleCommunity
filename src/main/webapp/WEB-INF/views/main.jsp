<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.wp.termproject.*" %>
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<style>
   body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
	}
	td{
		padding: 5px 30px;
		text-align: center;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<h1>게시글 목록</h1><hr>
	<h3>${name}님 반갑습니다!</h3>
	<form action="${pageContext.request.contextPath}/userlist/search" method="POST">
		<select name="search_type" id="search-select">
 				 	<option value="title">제목</option>
  				<option value="content">내용</option>
  				<option value="id">작성자</option>
		</select>
		<input type="hidden" name="permission" value="${permission}" />
		<input type="text" name="search_string" required/>
		<input type="submit" value="검색" />
		<input type="button" onclick="location='${pageContext.request.contextPath}/userlist/logout'" value="로그아웃" />
		<input type="button" onclick="location='${pageContext.request.contextPath}/userlist/main'" value="전체목록" />
		<input type="button" onclick="location='${pageContext.request.contextPath}/userlist/write?mod=0'" value="글쓰기" />
		<input type="button" onclick="location='${pageContext.request.contextPath}/userlist/userinfo'" value="사용자 정보 수정" />
		<c:if test="${permission == '1 '}">
			<input type="button" onclick="location='${pageContext.request.contextPath}/userlist/list'" value="사용자 관리"/>
		</c:if>
	</form>
	<br>
	<table align="center">
		<thead>
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성시간</th></tr>
		</thead>
		<tbody>
			<c:if test="${board_list != null}">
			<c:forEach var="board" items="${board_list}">
			<tr>
				<td>${board.rownum}</td>
				<td><a href="${pageContext.request.contextPath}/userlist/show?bid=${board.id}&bday=${board.day}&mod=0">${board.title}</a></td>
				<td>${board.id}</td>
				<td>${board.day}</td>
			</tr>
			</c:forEach>
			</c:if>
			<c:if test="${board_list == null}">
			<tr>
				<td colspan="4">등록, 검색된 게시글이 없습니다!</td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
</body>
</html>