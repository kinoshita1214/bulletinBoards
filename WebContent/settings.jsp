<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	<link href = "css/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>
<div class = "main-contents">

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

<form action = "settings" method = "post"><br />
	<input type = "hidden" name = "id" value = "${ editUser.id }" />
	<label for = "login_id">ログインID</label>
	<input name = "login_id" value = "${ editUser.login_id }"  /><br />

	<label for = "name">ユーザー名</label>
	<input name = "name" value = "${ editUser.name }" /><br />

	<label for = "password">パスワード</label>
	<input name = "password" type = "password" id = "password" /><br />

	<label for = "password">パスワード(確認用)</label>
	<input name = "check_password" type = "password" id = "password" /><br />

	<label for = "branch_id">支店名</label><br />
	<select name = "branch_id">
		<c:forEach var = "branch" items = "${ branch }">
			<c:if test = "${ branch.id == editUser.branch_id }">
				<option value = "${ branch.id }" selected = "${ branch.name }" > ${ branch.name }</option>
			</c:if>
			<c:if test = "${ branch.id != editUser.branch_id }">
				<option value = "${ branch.id }"  > ${ branch.name }</option>
			</c:if>

		</c:forEach>
	</select><br />

	<label for = "department_id">部署・役所名</label><br />
	<select name = "department_id">
		<c:forEach var = "department" items = "${ department }">
			<c:if test = "${ department.id == editUser.department_id }">
				<option value = "${ department.id }" selected = "${ department.name }" > ${ department.name }</option>
			</c:if>
			<c:if test = "${ department.id != editUser.department_id }">
				<option value = "${ department.id }"  > ${ department.name }</option>
			</c:if>
		</c:forEach>
	</select><br />
	<input type = "submit" value = "登録" /><br />
	<a href = "./">戻る</a>
</form>
<div class = "copyright">Copyright(c)keisuke kinoshita</div>
</div>
</body>
</html>