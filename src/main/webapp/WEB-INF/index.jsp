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
    	<h1 class="fushia">Welcome!</h1>    	
    	
    	
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
    	
    	<div class="col mt-3">
	    	<h2> Register </h2>
	    
		    <form:form action="/register" method="post" modelAttribute="newUser">
		        <div class="form-group">
		            <label>First Name:</label>
		            <form:input path="firstName" class="form-control" />
		            <form:errors path="firstName" class="text-danger" />
		        </div>
		         <div class="form-group">
		            <label>Lasts Name:</label>
		            <form:input path="lastName" class="form-control" />
		            <form:errors path="lastName" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Email:</label>
		            <form:input path="email" class="form-control" />
		            <form:errors path="email" class="text-danger" />
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
		        <div class="form-group">
		            <label>Password:</label>
		            <form:password path="password" class="form-control" />
		            <form:errors path="password" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Confirm Password:</label>
		            <form:password path="confirm" class="form-control" />
		            <form:errors path="confirm" class="text-danger" />
		        </div>
		        <input type="submit" value="Register" class="btn btn-primary mt-3" />
		    </form:form>
    	</div>
    	
    	<div class="col mt-3">
	    	<h2> Login </h2>
	    
		    <form:form action="/login" method="post" modelAttribute="newLogin">
		        <div class="form-group">
		            <label>Email:</label>
		            <form:input path="email" class="form-control" />
		            <form:errors path="email" class="text-danger" />
		        </div>
		        <div class="form-group">
		            <label>Password:</label>
		            <form:password path="password" class="form-control" />
		            <form:errors path="password" class="text-danger" />
		        </div>
		        <input type="submit" value="Login" class="btn btn-success mt-3" />
		    </form:form>
	    </div>
    </div>
    
</body>
</html>

