<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="calen_link/calendar.css">
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
<div class="main-contents">
<h2>ホーム</h2>
	<c:if test="${ not empty loginUser }">
		<a href="newPost">
			<input type="hidden" name = "branch_id" value = "${ user.branch_id }" />
			<input type="hidden" name = "department_id" value = "${ user.department_id }" />
			新規投稿画面
		</a>
		<a href="management">ユーザー管理画面</a>
		<a href="logout">ログアウト</a>
	</c:if>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
		<div class="login_id">
			@<c:out value="${loginUser.login_id}" />
		</div>

	</div>
</c:if>

<br />
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message" >
				<li><c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<form action = "./" method = get>
	<div class = "date">
		<label for = "category">日付検索</label><br />
		<input type="date" name="start" value = "${ start }">

		<input type="date" name="end" value = "${ end }">
	</div>

	<div class = "category">
		<label for = "category">カテゴリー検索</label><br />
		<select  name = "category">
		<option value="">選択してください</option>
			<c:forEach var = "categories" items = "${ categories }">
				<option value = "${ categories.category }" > ${ categories.category }</option>
			</c:forEach>
		</select>
		<input type = "submit" value = "検索">
	</div>
</form>
<div class="posts">
	<c:forEach items="${ posts }" var="post">
		<form action="deletePost" method="post" onSubmit="return check()">
			<div class="login_id-name">
				<input type = "hidden" name = "login_id" value = "${ post.login_id }" />
				<span class="name"><c:out value = "${ post.name }" /></span>
			</div>
			<div class = "subject">件名:<c:out value = "${ post.subject }" /></div>
			本文:
			<div class = "text"><c:out value = "${post.text}" /></div>
			<div class = "category">カテゴリー:<c:out value = "${ post.category }" /></div>
			<div class = "date"><fmt:formatDate value = "${ post.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<input type="hidden" name = "post.id" value = "${ post.id }" />
			<c:if test = "${ post.user_id == user.id || loginUser.department_id == 2 || (loginUser.branch_id == post.branch_id && loginUser.department_id <= post.department_id)}">
				<input type = "submit" value = "削除" />
			</c:if>
		</form>

		<div class="comments">
			<c:forEach items="${ comments }" var="comment">
				<c:if test = "${ comment.post_id == post.id }">
					<form action = "deleteComment" method = "post" onSubmit="return check()">
						<div class="login_id-name">
							<span class="login_id"></span>
							<span class="name"><c:out value = "${comment.name}" /></span>
							<span class="post_id"></span>
						</div>
						<div class = "text"><c:out value = "${comment.text}" /></div>
						<div class = "date"><fmt:formatDate value = "${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
						<input type="hidden" name = "comment.id" value = "${ comment.id }" />
						<c:if test = "${ comment.user_id == user.id }">
							<input type = "submit" value = "削除">
						</c:if>
					</form>
				</c:if>
			</c:forEach>
		</div>

		<div class="form-area">
			コメント(500文字まで)<br />
			<form action="newComment" method="post" >
				<input type = "hidden" name = "post_id" value = "${ post.id }"/>
				<textarea name="text" cols="100" rows="5" class="comment-box"></textarea>
				<br />
				<input type="submit" value="コメント">
			</form>
		</div>
	</c:forEach>
</div>

</div>
</body>
</html>