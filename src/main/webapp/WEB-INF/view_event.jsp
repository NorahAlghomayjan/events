<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

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
		<h1 class="col fushia">Welcome!</h1>
		<h3 class="col text-end">
			<a href="/logout"> Logout </a>
		</h3>


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
			<div class="col">
				<h1>	<c:out value="${event.name }"/>	</h1>
				<p>	host: <c:out value="${event.host.firstName }"/>	</p>
				<p>	date: <c:out value="${event.date }"/>	</p>
				<p>	location: <c:out value="${event.location }"/>	</p>
				<p>	number of people in event: <c:out value="${event.joinedUsers.size() }"/>	</p>
				<table class="container">
					<thead>
						<th>Name:</th>
						<th>Location:</th>
					</thead>
					<tbody>
					<c:forEach var="user" items="${event.joinedUsers }">
						<tr>
							<td>	<c:out value="${user.firstName }"/> <c:out value="${user.lastName }"/>	</td>
							<td>	<c:out value="${user.location }"/> </td>
						</tr>
						</c:forEach>
					</tbody>
				
				</table>
			</div>
		</div>
		
		<div class="col">
		<div>
			<c:forEach var="comment" items="${comments}">
				<p>	<c:out value="${comment.msg}"/><p>
			</c:forEach>
		</div>
		<h2> add comment </h2>
		<form:form action="/comments/new" method="post" modelAttribute="comment">
		        <div class="form-group">
		            <label>Msg:</label>
		            <form:input path="msg" class="form-control" />
		            <form:errors path="msg" class="text-danger" />
		        </div>
		        <input type="submit" value="Add" class="btn btn-primary mt-3" />
		    </form:form>
		</div>
	</div>

</body>
</html>

