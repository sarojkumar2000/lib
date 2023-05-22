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
<title>Add Book to student</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
			<h1><li class="nav-item"><a href="/" class="btn btn-outline-success	">Home</a></li></h1>	&nbsp;		
				<h1><li class="nav-item"><a href="bookstakenbystudent?id=${student_id}" class="btn btn-outline-success	">Back</a></li></h1>	
			</ul>
			
		</div>
		<ul class="navbar-nav">
			<li class="nav-item" ><a href="/selectuser" class="btn btn-outline-success" >Logout</a></li>
		</ul>
	
	</nav>
<h1>Add book to ${studentName}</h1>
	<div class="container">
<table class="table">		
<form:form method="POST" modelAttribute="book">
		<label for="bookId">Select a book:</label>
    <select name="bookId" id="book_id">
        <c:forEach var="book" items="${books}">
            <option value="${book.book_id}">${book.title}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="studentId" value="${student.id}">
    <input type="submit" value="Add Book">


			<%-- Book id:&nbsp;<form:input type="text" path="book_id" required="required"/><br><br>  
			Student id:&nbsp;<form:input type="text" path="id" required="required"/><br><br>  
			
			
			<br>
			<input type="submit"> --%>
			
		</form:form></br>
	<c:if test="${not empty msg}">
  			  <div class="alert alert-danger">
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