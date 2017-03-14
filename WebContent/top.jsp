<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href = "css/style.css" rel = "stylesheet" type = "text/css">
	<link rel="stylesheet" type="text/css" href="calen_link/calendar.css">
	<script type="text/javascript">
<!--

function check(){

	if(window.confirm('削除してよろしいですか？')){ // 確認ダイアログを表示

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
<div class = "header">
	<c:if test="${ not empty loginUser }">
		<a href="newPost">
			<input type="hidden" name = "branch_id" value = "${ user.branch_id }" />
			<input type="hidden" name = "department_id" value = "${ user.department_id }" />
			新規投稿
		</a>
		<c:if test = "${ user.department_id == 1 }">
			<a href="management"> ユーザー管理</a>
		</c:if>
		<a href="logout"> ログアウト</a>
	</c:if>
</div>
<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name"><h3><c:out value="${loginUser.name}" /></h3></div>
		<div class="login_id">
			<input type = "hidden" value="${loginUser.login_id}" />
		</div>

	</div>
</c:if>

<br />
<c:if test = "${ not empty errorMessages }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var = "message" >
				<c:out value = "${ message }" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session" />
</c:if>
<form action = "./" method = get>
	<div class = "date">
		<label for = "category">日付検索</label><br />
		<input type="date" name="start" value = "${ start }"> ～

		<input type="date" name="end" value = "${ end }">
	</div>
	<div class = "category">
		<label for = "category">カテゴリー検索</label><br />
		<select  name = "category" style=" width: 300px">
		<option value="">すべて</option>
			<c:forEach var = "categories" items = "${ categories }">
				<c:if test = "${ categories.category == category.category }">
					<option value = "${ categories.category }" selected = "${ categories.category }" > ${ categories.category }</option>
				</c:if>
				<c:if test = "${ categories.category != category.category }">
					<option value = "${ categories.category }" > ${ categories.category }</option>
				</c:if>
			</c:forEach>
		</select>
		<br /><input type = "submit" value = "検索">    <input type = "reset" value = "リセット">
	</div>
</form>
<br />
<div class="posts">
	<c:forEach items="${ posts }" var="post">
		<div class = "post">
			<form action="deletePost" method="post" onSubmit="return check()">
				<div class="login_id-name">
					<input type = "hidden" name = "login_id" value = "${ post.login_id }" />
					<span class="name"><c:out value = "${ post.name }" />さん</span>
				</div>
				<div class = "subject">件名:<c:out value = "${ post.subject }" /></div>
				本文<br />
				<div class = "text1">
					<c:forEach var="str" items="${ fn:split(post.text,'
					') }" >
						<c:out value ="${str}"/><br>
					</c:forEach>
				</div>
				<div class = "category">カテゴリー:<c:out value = "${ post.category }" /></div>
				<div class = "date"><fmt:formatDate value = "${ post.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				<input type="hidden" name = "post.id" value = "${ post.id }" />
				<c:if test = "${ post.user_id == user.id || loginUser.department_id == 2 || (loginUser.branch_id == post.branch_id && loginUser.department_id <= post.department_id)}">
					<input class = "button2" type = "submit" value = "削除" />
				</c:if>
			</form>
		</div>
		<div class="comments">
			<c:forEach items="${ comments }" var="comment">
				<c:if test = "${ comment.post_id == post.id }">
					<div class = "comment">
						<form action = "deleteComment" method = "post" onSubmit="return check()">
							<div class="login_id-name-date">
								<span class="login_id"></span>
								<span class="name"><c:out value = "${ comment.name }" />:</span>
								<span class="post_id"></span>
								<span class = "date"><fmt:formatDate value = "${ comment.insertDate }" pattern="yyyy/MM/dd HH:mm:ss" /></span>
							</div>
							<div class = "text2">
								<c:forEach var="str" items="${ fn:split(comment.text,'
								') }" >
									<c:out value = "${str}"/><br>
								</c:forEach>
							</div>
							<input type="hidden" name = "comment.id" value = "${ comment.id }" />
							<c:if test = "${ comment.user_id == user.id || loginUser.department_id == 2 || (loginUser.branch_id == post.branch_id && loginUser.department_id <= post.department_id)}">
								<input class = "button2" type = "submit" value = "削除">
							</c:if>
						</form>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="form-area">
			コメント(500文字まで)<br />
			<form action="newComment" method="post" >
				<input type = "hidden" name = "post_id" value = "${ post.id }"/>
				<textarea name="text" cols="109" rows="5" style = "font-size: 17px" class="comment-box"></textarea>
				<br />
				<input type="submit" value="コメント">
			</form>
		</div>
	</c:forEach>
</div>

</div>
</body>
</html>