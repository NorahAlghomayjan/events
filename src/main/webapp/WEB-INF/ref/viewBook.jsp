<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login and Registration</title>
<link rel='stylesheet' href='/webjars/bootstrap/5.1.2/css/bootstrap.min.css'>
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
	    	<h2 class="col"> Add a Book to Your Shelf </h2>
	    	<a class="col text-end" href="/books"> Go back to the shelves..</a>
		</div>
		<c:if test="${username == book.user.userName}">
			<h4> <span class="red">you</span> reads <span class="fushia"><c:out value="${book.title }"/></span>  by <span class="green"><c:out value="${book.author }"/></span> </h4>
		</c:if>
		<c:if test="${username != book.user.userName}">
			<h4> <span class="red"><c:out value="${username }"/></span> reads <span class="fushia"><c:out value="${book.title }"/></span>  by <span class="green"><c:out value="${book.author }"/></span> </h4>
		</c:if>
			
		<div class="container p-5">
		
			<h5>Here are <c:out value="${book.user.userName }"/>'s thoughts:</h5>
			<hr>
			<p><c:out value="${book.thoughts }"/></p>	
			<hr>
		
		<a class="btn btn-light offset-md-11" href="/books/${book.id }/edit"> edit</a>
		</div>
    </div>
    
</body>
</html>

