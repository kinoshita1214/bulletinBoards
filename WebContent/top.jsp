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
</head>
<body>
<div class="main-contents">

<div class="header">

	<c:if test="${ not empty loginUser }">
		<a href="post">新規投稿画面</a>
		<a href="management">ユーザー管理画面</a>
		<a href="logout">ログアウト</a>
	</c:if>
</div>

<c:if test="${ not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}" /></h2></div>
		<div class="login_id">
			@<c:out value="${loginUser.login_id}" />
		</div>

	</div>
</c:if>


<div class="posts">
	<c:forEach items="${ posts }" var="post">
		<div class="login_id-name">
			<span class="login_id"><c:out value = "${post.login_id}" /></span>
			<span class="name"><c:out value = "${post.name}" /></span>
		</div>
		件名:<div class = "subject"><c:out value = "${ post.subject }" /></div>
		本文:<div class = "text"><c:out value = "${post.text}" /></div>
		カテゴリー:<div class = "category"><c:out value = "${ post.category }" /></div>
		<div class = "date"><fmt:formatDate value = "${post.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>

			<div class="comments">
				<c:forEach items="${ comments }" var="comment">
				<c:if test = "${ comment.post_id == post.id }">
					コメント<br />
					<div class="login_id-name">
						<span class="login_id"><c:out value = "${comment.login_id}" /></span>
						<span class="name"><c:out value = "${comment.name}" /></span>
						<span class="post_id"></span>
					</div>
					<div class = "text"><c:out value = "${comment.text}" /></div>
					<div class = "date"><fmt:formatDate value = "${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
				</c:if>
				</c:forEach>
			</div>

		<div class="form-area">
			コメント<br />
			<form action="newComment" method="post" >
				<input type = "hidden" name = "post_id" value = "${ post.id }"/>
				<textarea name="text" cols="100" rows="5" class="comment-box"></textarea>
				<br />
				<input type="submit" value="コメント">（500文字まで）
			</form>
		</div>
	</c:forEach>
</div>

<div class="copyright">Copyright(c)keisuke kinoshita</div>
</div>
</body>
</html>