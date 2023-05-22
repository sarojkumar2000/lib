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
<title>Home</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
					
				
			</ul>
			
		</div>
	<ul class="navbar-nav">
			<li class="nav-item" ><a href="/selectuser" class="btn btn-outline-success" >Logout</a></li>
		</ul>
	</nav>
	
	<div class="container">
	<div class="alert alert-light">
	<H1>Books taken by ${studentName}</H1>
	
<table class="table">
			<thead>
				<tr>
					
					<th>Book Id</th>
					<th>Book title</th>
					<th>Book author</th>
					<th>Book publisher</th>
					
					
				</tr>
			</thead>


			<tbody>

				<c:forEach items="${books}" var="book">
					<tr>

						<td>${book.book_id}</td>
						<td>${book.title }</td>
						<td>${book.author }</td>
						<td>${book.publisher }</td>
						<%-- <td><a href="return?id=${book.book_id }"
							class="btn btn-warning">Return </a></td> --%>

					</tr>

					

				</c:forEach>

			</tbody>
		



		</table></div>
		<c:if test="${not empty noBooksMsg}">
  			  <div class="alert alert-primary">
       				 ${noBooksMsg}
   				 </div>
		</c:if>

	
		</div>


</body>
</html>