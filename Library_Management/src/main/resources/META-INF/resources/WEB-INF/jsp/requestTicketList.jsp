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
<title>All Books</title>
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


			<thead>
				 <tr>
        <th>Student ID</th>
        <th>Book ID</th>
        <th>Request Date</th>
        <th>Status</th>
    </tr>
			</thead>


			<tbody>

				 <c:forEach var="ticket" items="${tickets}">
        <tr>
            <td>${ticket.studentId}</td>
            <td>${ticket.bookId}</td>
            <td>${ticket.requestDate}</td>
            <td>${ticket.status}</td>
            <td><a href="addRequestedBook?id=${ticket.studentId }&book_id=${ticket.bookId}" class="btn btn-success">Add this book</a></td>
        </tr>
    </c:forEach>


			</tbody>
			</div>
		


		<c:if test="${not empty msg}">
  			  <div class="alert alert-primary">
       				 ${msg}
   				 </div>
		</c:if>
		

		</table>
		
		

	

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

	<script
		src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</body>
</html>