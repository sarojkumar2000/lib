<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Add Student</title>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
			<h1><li class="nav-item"><a href="/" class="btn btn-outline-success	">Home</a></li></h1>	&nbsp;		
			<h1><li class="nav-item"><a href="/studentslist" class="btn btn-outline-success	">Back</a></li></h1>		
			</ul>
			
		</div>
		<ul class="navbar-nav">
			<li class="nav-item" ><a href="/selectuser" class="btn btn-outline-success" >Logout</a></li>
		</ul>
	
	</nav>

	<div class="container">
<table class="table">	
<div class="alert alert-light">
	<h1>Add new student</h1>
<form:form method="POST" modelAttribute="student">
			Student id:&nbsp;<form:input type="text" class="form-control" path="id" required="required"/>
			name:&nbsp;<form:input type="text" class="form-control" path="name" required="required"/>
			course:&nbsp;<form:input type="text" class="form-control" path="course" required="required"/>
			Branch:&nbsp;<form:input type="text" class="form-control" path="branch" required="required"/>
			<br>
			<input type="submit" class="btn btn-primary">
			
		</form:form>

		
</table>
	</div>

</div>



<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

	<script
		src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</body>
</html>