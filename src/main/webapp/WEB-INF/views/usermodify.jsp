<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<style>
	body{
		text-align: center;
	}
	.field{
		margin: 5px 0;
	}
	#msg{
		color: red;
	}
	label {
		width:100px;	
		float:left;
		text-align: right;
	}
</style>
<script>
	function confirmAndDelete(id)
	{
		let result = confirm('"' + id + '"정말로 탈퇴하시겠습니까?');
		if(result)
		{
			let formEl = document.getElementById('user_form');
			formEl.setAttribute("action","${pageContext.request.contextPath}/userlist?action=delete&id=" + id + "&mod=1");
			formEl.submit();
		}
	}
</script>
</head>
<body>
	<h1>회원정보 수정</h1><hr><br>
	<form id="user_form" action="${pageContext.request.contextPath}/userlist/usermodify" method="POST">
		<fieldset style="width: 300px; margin:auto;">
			<legend>회원정보</legend>
			<div class="field">
				<label for="sign_name">이름 :</label>
				<input type="text" name="sign_name" value="${name}" required/>
			</div>
			<div class="field">
				<label for="sign_id">아이디 :</label>
				<input type="text" name="sign_id" value="${id}" required/>
			</div>
			<div class="field">
				<label for="sign_passwd1">비밀번호 :</label>
				<input type="password" name="sign_passwd1" required/>
			</div>
			<div class="field">
				<label for="sign_passwd2">비밀번호 확인 :</label>
				<input type="password" name="sign_passwd2" required/>
			</div>
			<input type="hidden" name="id" value="${id}" />
			<div class="field">
				<input type="submit" value="수정"/>
				<button href='#' onclick='confirmAndDelete("${id}");'>회원 탈퇴</button>
			</div>
		</fieldset>
		<div id="msg">
			<br>
			<c:if test="${msg != null}">
				${msg}
			</c:if>
		</div>
	</form>
</body>
</html>