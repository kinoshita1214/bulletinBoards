<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
	<link href = "css/style.css" rel = "stylesheet" type = "text/css">
	<TITLE></TITLE>
</head>
<body>
<div class = "main-contents">
<h2>新規ユーザー登録</h2>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message">
				<li><c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<form action = "signup" method = "post"><br />
	<label for = "login_id">ログインID(半角英数字6文字以上20文字以下)</label><br />
	<input name = "login_id" id = "login_id" value = "${ user.login_id }"/><br />

	<label for = "name">ユーザー名(10文字以下)</label><br />
	<input name = "name" id = "name" value = "${ user.name }" /><br />

	<label for = "password">パスワード(6文字以上255文字以下)</label><br />
	<input name = "password" type = "password" id = "password" /><br />

	<label for = "password">パスワード(確認用)</label><br />
	<input name = "check_password" type = "password" id = "password" /><br />

	<label for = "branch_id">支店名</label><br />

	<select name = "branch_id">
		<c:forEach var = "branch" items = "${ branch }">
			<c:if test = "${ user.branch_id == null }">
				<option value = "${ branch.id }" > ${ branch.name }</option>

				<c:if test = "${ branch.id == user.branch_id }">
					<option value = "${ branch.id }" selected = "${ branch_id }" > ${ branch.name }</option>
				</c:if>
			</c:if>
			<c:if test = "${ branch_id != user.branch_id  }">
				<option value = "${ branch.id }" > ${ branch.name }</option>
			</c:if>
		</c:forEach>

	</select><br />
	<label for = "department_id">部署・役所名</label><br />
	<select name = "department_id">
		<c:forEach var = "department" items = "${ department }">
			<c:if test = "${ user.department_id == null }">
				<option value = "${ department.id }" > ${ department.name }</option>

				<c:if test = "${ department.id == user.department_id }">
					<option value = "${ department.id }" selected = "${ department_id }" > ${ department.name }</option>
				</c:if>
			</c:if>
			<c:if test = "${ department_id != user.department_id  }">
				<option value = "${ department.id }" > ${ department.name }</option>
			</c:if>
		</c:forEach>
	</select><br />
	<input type = "submit" value = "登録" /><br />
	<a href = "management">戻る</a>
</form>
</div>
</body>
</html>