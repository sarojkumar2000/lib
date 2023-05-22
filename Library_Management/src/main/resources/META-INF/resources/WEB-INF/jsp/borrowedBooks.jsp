<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css"
	rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Borrowed books</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
			<h1><li class="nav-item"><a href="/" class="btn btn-outline-success	">Home</a></li></h1>	&nbsp;		
			<h1><li class="nav-item"><a href="/" class="btn btn-outline-success	">Back</a></li></h1>	
			</ul>
			
		</div>
	<ul class="navbar-nav">
			<li class="nav-item" ><a href="/selectuser" class="btn btn-outline-success" >Logout</a></li>
		</ul>
	</nav>
	
	<div class="container">
	<table class="table">
    <tr>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Book ID</th>
        <th>Book Title</th>
        <th>Book Author</th>
      
        <th>Number of Available</th>
    </tr>
    
    <c:forEach var="books_taken" items="${books_taken}">
        <tr>
            <td>${books_taken.student.id}</td>
            <td>${books_taken.student.name}</td>
            <td>${books_taken.book.book_id}</td>
            <td>${books_taken.book.title}</td>
            <td>${books_taken.book.author}</td>
            <td>${books_taken.book.no_of_available}</td>
            <td><a href="bookstakenbystudent?id=${books_taken.student.id }" class="btn btn-success">Go to Student</a></td>
        </tr>
    </c:forEach>
</table>
</div>
	
	
	
		

	

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

	<script
		src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</body>
</html>