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
<h2>ユーザー編集</h2>
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message">
				<c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>

<div class = "settings">
	<form action = "settings" method = "post"><br />
		<input type = "hidden" name = "id" value = "${ editUser.id }" />
		<label for = "login_id">ログインID(半角英数字6文字以上20文字以下)</label><br />
		<input name = "login_id" size ="35" style ="font-size: 12pt" value = "${ editUser.login_id }"  /><br /><br />

		<label for = "name">ユーザー名(10文字以下)</label><br />
		<input name = "name" size = "16" style ="font-size: 12pt" value = "${ editUser.name }" /><br /><br />

		<label for = "password">パスワード(6文字以上255文字以下)</label><br />
		<input name = "password" type = "password" id = "password" size = "35" style ="font-size: 12pt"/><br /><br />

		<label for = "password">パスワード(確認用)</label><br />
		<input name = "check_password" type = "password" id = "password" size = "35" style ="font-size: 12pt"/><br /><br />


		<c:if test = "${ loginUser.id != editUser.id  }">
			<label for = "branch_id">支店名</label><br />
			<select  name = "branch_id" style="width: 150px " >
				<c:forEach var = "branch" items = "${ branch }">

					<c:if test = "${ branch.id == editUser.branch_id }">
						<option value = "${ branch.id }" selected = "${ branch.name }" > ${ branch.name }</option>
					</c:if>
					<c:if test = "${ branch.id != editUser.branch_id }">
						<option value = "${ branch.id }"  > ${ branch.name }</option>
					</c:if>
				</c:forEach>
			</select><br /><br />
		</c:if>


		<c:if test = "${ loginUser.id != editUser.id  }">
			<label for = "department_id">部署・役所名</label><br />
			<select name = "department_id" style="width: 150px">
				<c:forEach var = "department" items = "${ department }">
					<c:if test = "${ department.id == editUser.department_id }">
						<option value = "${ department.id }" selected = "${ department.name }" > ${ department.name }</option>
					</c:if>
					<c:if test = "${ department.id != editUser.department_id }">
						<option value = "${ department.id }"  > ${ department.name }</option>
					</c:if>
				</c:forEach>
			</select><br /><br />
		</c:if>
		<input type = "submit" value = "登録" /><br />

	</form>
</div>
<div class = "back">
	<a href = "management" >戻る</a>
</div><br/>
<div class="copyright">Copyright(c)Keisuke Kinoshita</div>
</div>
</body>
</html>