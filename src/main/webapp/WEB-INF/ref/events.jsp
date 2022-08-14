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
		<h5>Here are some events in your state</h5>

		<table class="table">
			<thead>
				<th>Name</th>
				<th>Date</th>
				<th>Location:</th>
				<th>Host:</th>
				<th>Actions/Status:</th>
			</thead>
			<c:forEach var="event" items="${currentUser.joinedEvents }">
				<c:if test="${ user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td>joining <a href="events/${event.id }/cancel">cancel</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="event" items="${currentUser.createdEvents }">
				<c:if test="${ user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td><a href="events/${event.id }/edit">Edit</a> | <a href="events/${event.id }/delete">Delete</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="event" items="${notJoinedEvents }">
				<c:if test="${ user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td><a href="events//${event.id }/join">Join</a></td>
					</tr>
				</c:if>
			</c:forEach>

			<tbody>
			</tbody>
		</table>
		
		<table class="table">
			<thead>
				<th>Name</th>
				<th>Date</th>
				<th>Location:</th>
				<th>Host:</th>
				<th>Actions/Status:</th>
			</thead>
			<c:forEach var="event" items="${currentUser.joinedEvents }">
				<c:if test="${ !user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td>joining <a href="events/${event.id }/cancel">cancel</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="event" items="${currentUser.createdEvents }">
				<c:if test="${ !user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td><a href="events/${event.id }/edit">Edit</a> | <a href="events/${event.id }/delete">Delete</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="event" items="${notJoinedEvents }">
				<c:if test="${ !user.location.equals(event.location)}">
					<tr>
						<td><c:out value="${event.name }" /></td>
						<td><c:out value="${event.date }" /></td>
						<td><c:out value="${event.location }" /></td>
						<td><c:out value="${event.host.firstName }" /></td>
						<td><a href="events/${event.id }/join">Join</a></td>
					</tr>
				</c:if>
			</c:forEach>

			<tbody>
			</tbody>
		</table>

		<div class="">
		<h2> create an event </h2>
		<form:form action="/events/new" method="post" modelAttribute="event">
		        <div class="form-group">
		            <label>Name:</label>
		            <form:input path="name" class="form-control" />
		            <form:errors path="name" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Date:</label>
		            <form:input type="date" path="date" class="form-control" />
		            <form:errors path="date" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Location:</label>
		            <form:select path="location" class="form-select">
		            	<option value="${null }">select location</option>
		            	<c:forEach var="location" items="${locations}">
		            		<form:option value="${location }" path="location">
		            			<c:out value="${location }"/>
		            		</form:option>
		            	</c:forEach>	            
		            </form:select>
		            <form:errors path="location" class="text-danger" />
		        </div>
		        <input type="submit" value="Add" class="btn btn-primary mt-3" />
		    </form:form>
		</div>
	</div>

</body>
</html>

