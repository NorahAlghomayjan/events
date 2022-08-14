<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login & Registration</title>
<link rel='stylesheet' href='/webjars/bootstrap/5.1.2/css/bootstrap.min.css'>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="gray"> 	
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
    	
    	<div class="container row mt-3">
	    	<h2 class="col"> Change your Entry </h2>
	    	<a class="col text-end" href="/books"> Go back to the shelves..</a>
	    
	    	<div class="w-100 mt-2"></div>
		    <form:form class="row" action="/books/${bookId}/edit" method="put" modelAttribute="book">
		        <div class="form-group">
		            <label>Title:</label>
		            <form:input path="title" class="form-control" />
		            <form:errors path="title" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Author:</label>
		            <form:input path="author" class="form-control" />
		            <form:errors path="author" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>My Thoughts:</label>
		            <form:input path="thoughts" class="form-control" />
		            <form:errors path="thoughts" class="text-danger" />
		        </div>
		        <input type="submit" value="edit" class="btn btn-light mt-3 col offset-md-6" />
		        <a class="btn btn-danger col mt-3 offset-md-1" href="/books/${bookId }/delete"> delete book </a>
		    </form:form>
    	</div>
    	
</body>
</html>

