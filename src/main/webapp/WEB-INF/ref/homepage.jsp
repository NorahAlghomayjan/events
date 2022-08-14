<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login and Registration</title>
<link rel='stylesheet'
	href='/webjars/bootstrap/5.1.2/css/bootstrap.min.css'>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="container m-5 row">

		<!-- Success Msg  -->
		<c:if test="${success != null}">
			<div class="alert alert-success">
				<c:out value="${success}" />
			</div>
		</c:if>
		<!-- Error Msg  -->
		<c:if test="${danger != null}">
			<div class="alert alert-danger">
				<c:out value="${danger}" />
			</div>
		</c:if>

		<!--  -->

		<div class="row">
			<h1 class="fushia col-5">
				Welcome
				<c:out value="${user.userName }" />
				!
			</h1>
			<a class="col text-end" href="/logout"> logout </a>
			<div class="w-100 mt-2"></div>
			<a class="col text-end" href="/books/new"> add a book To my Shelf</a>
		</div>
		
		
		<h5 class="mt-5">Books to borrow ..</h5>

		<div>
			<table class="table mt-2">
				<thead>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Owner</th>
					<th>Actions</th>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books }">
						<c:if test="${book.borrower.id != user.id }">
							<tr>
								<td><c:out value="${book.id }" /></td>
								<td><c:out value="${book.title }" /></td>
								<td><c:out value="${book.author }" /></td>
								<td><c:out value="${book.user.userName }" /></td>
								<c:if
									test="${book.borrower == null && book.user.id != user_id }">
									<td><a href="/books/${book.id}/borrow">borrow</a></td>
								</c:if>
								<c:if test="${book.user.id == user_id }">
									<td><a href="/books/${book.id}/edit">edit</a> | <a
										href="/books/${book.id}/delete">delete</a></td>
								</c:if>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>

		</div>
		
		<h5 class=" mt-5">Books I'm borrowing..</h5>

		<div>
			<table class="table mt-2">
				<thead>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Owner</th>
					<th>Actions</th>
				</thead>
				<tbody>
					<c:forEach var="book" items="${user.borrowedBooks }">
						<tr>
							<td><c:out value="${book.id }" /></td>
							<td><c:out value="${book.title }" /></td>
							<td><c:out value="${book.author }" /></td>
							<td><c:out value="${book.user.userName }" /></td>
							<td><a href="/books/${book.id }/return">return</a></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>

		</div>
	</div>

</body>
</html>

