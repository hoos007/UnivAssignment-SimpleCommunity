<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.wp.termproject.*" %>
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글</title>
<style>
	h1{
		text-align: center;
	}
	.field{
		margin: 5px 0;
	}
</style>
<script>
	function confirmAndDelete(title)
	{
		let result = confirm('"' + title + '" 게시글을 정말로 삭제하시겠습니까?');
		if(result)
		{
			let formEl = document.getElementById('board_form');
			formEl.setAttribute("action","${pageContext.request.contextPath}/userlist/delete?bid=${board.id}&bday=${board.day}");
			formEl.submit();
		}
	}
</script>
</head>
<body>
	<h1>게시글 내용</h1><hr><br>
	
		<fieldset style="width: 600px; margin:auto;">
			<legend>게시글</legend>
			<div class="field">
				${board.title}
			</div>
			<div class="field">
				<pre>${board.content}</pre>
			</div>
			<div class="field">
				${board.id}
				${board.day}
			</div>
			<button onclick="location.href='${pageContext.request.contextPath}/userlist/main'">메인화면으로</button>
			<c:if test="${bid == userid || permission == '1 '}">
				<button onclick="location.href='${pageContext.request.contextPath}/userlist/show?bid=${board.id}&bday=${board.day}&mod=1'">수정</button>
				<form style="display: inline;" id='board_form' action='' method='POST'>
				<button href='#' onclick='confirmAndDelete("${board.title}");'>삭제</button>
				</form>
			</c:if>
		</fieldset>
</body>
</html>