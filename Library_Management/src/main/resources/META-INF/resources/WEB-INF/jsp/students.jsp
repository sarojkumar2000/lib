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
<title>All Students</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
			<h1><li class="nav-item"><a href="/" class="btn btn-outline-success	">Home</a></li></h1>			
					
			</ul>
			
		</div>
		<ul class="navbar-nav">
			<li class="nav-item" ><a href="/selectuser" class="btn btn-outline-success" >Logout</a></li>
		</ul>
	
	</nav>
	   <div class="col-11 text-center">
        <input type="text" id="student" name="student" center placeholder="Enter student name">&nbsp;
<a href="#" class="btn btn-success" onclick="searchstudent()">Search</a>

    </div>
	
	
	<div class="row">
 
</div>
	        
<script>
    function searchstudent() {
        var student = document.getElementById("student").value;
        var searchUrl = "/searchStudent?student=" + encodeURIComponent(student);
        window.location.href = searchUrl;
    }
</script>
	<div class="container">
	
<table class="table">
			<thead>
				<tr>

					<th>Student Id</th>
					<th>Name</th>
					<th>Course</th>
					<th>Branch</th>
				
					
				</tr>
			</thead>


			<tbody>

				<c:forEach items="${allstudents}" var="student">
					<tr>

						<td>${student.id }</td>
						<td>${student.name }</td>
						<td>${student.course}</td>
						<td>${student.branch}</td>
						
						<td><a href="bookstakenbystudent?id=${student.id }" class="btn btn-success">Books taken</a></td>
						

					</tr>



				</c:forEach>
<c:if test="${not empty nostudentMsg}">
  			  <div class="alert alert-primary">
       				 ${nostudentMsg}
   				 </div>
		</c:if>

			</tbody>


		</table>
		<a href="addstudent" class="btn btn-success">Add student</a>
		</div>

	


	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

	<script
		src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</body>
</html>