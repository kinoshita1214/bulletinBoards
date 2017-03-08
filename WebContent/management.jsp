<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<script type="text/javascript">
<!--

function check(){

	if(window.confirm('送信してよろしいですか？')){ // 確認ダイアログを表示

		return true; // 「OK」時は送信を実行

	}
	else{ // 「キャンセル」時の処理

		window.alert('キャンセルされました'); // 警告ダイアログを表示
		return false; // 送信を中止

	}

}

// -->
</script>
</head>
<body>
<div class = "main-contents">
<h2>ユーザー管理画面</h2>
<div class="header">
	<c:if test="${ not empty loginUser }">
		<a href="signup">新規ユーザー登録</a>
		<a href="./">ホーム</a>
	</c:if>
</div>
<style type="text/css">

}
.table5 th {
  background-color: #cccccc;
}
.table5 td {
  text-align: center;
}
</style>
<div class = "managements">
		<div class = "management">
			<div class = "users">
				<div class="login_id-name">
					<table class = "table5" border = 1 width =500>
						<tr><th>ログインID</th><th>名前</th><th>支店名</th><th>部署・役所名</th><th colspan =3>状態</th></tr>
						<c:forEach items = "${ managements }" var = "management">
						<tr>
							<td><span class="login_id"><c:out value="${management.login_id}" /></span></td>
							<td><span class = "name"><c:out value="${management.name}" /></span></td>
							<td><span class = "branch_name"><c:out value="${management.branch_name}" /></span></td>
							<td><span class = "department_name"><c:out value="${management.department_name}" /></span></td>
							<td>
								<form action ="settings" method = "get">
									<input type = "hidden" name = "management.id" value = "${ management.id }" />
									<input type = "submit" value = "編集">
								</form>
							</td>
							<td>
								<c:if test = "${ management.id != loginUser.id }">
									<c:if test = "${ management.is_stoped == 0 }">
										<form action = "stop" method = "post" onSubmit="return check()">
											<input type = "hidden" name = "management.id" value = "${ management.id }" />
											<input type = "hidden" name = "is_stoped" value = 1 />
											<input type = "submit" value = "停止"/>
										</form>
									</c:if>

									<c:if test = "${ management.is_stoped ==  1 }">
										<form action = "stop" method = "post" onSubmit="return check()">
											<input type = "hidden" name = "management.id" value = "${ management.id }" />
											<input type = "hidden" name = "is_stoped" value = 0 />
											<input type = "submit" value = "復活"/>
										</form>
									</c:if>
								</c:if>
							</td>
							<td>
								<c:if test = "${ management.id != loginUser.id }">
									<form action = "deleteUser" method = "post" onSubmit="return check()">
										<input type = "hidden" name = "management.id" value = "${ management.id }" />
										<input type = "submit" value = "削除"/>
									</form>
								</c:if>
							</td>
						<tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
</div>
<div class = "copyright">Copyright(c)keisuke kinoshita</div>
</div>
</body>
</html>