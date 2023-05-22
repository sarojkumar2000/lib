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
<title>Student Login</title>
</head>
<body>
<h1>Login</h1>
    <form method="post" action="/studentlogin">
    <div class="container">
    <label for="id" class="form-label">Id</label>
        
        <input type="text" class="form-control" id="id" name="id"required />
        <br/>
            
  </div><div class="container">
        <label for="password" class="form-label">Password:</label>
        <input type="password" class="form-control"id="password" name="password"required />
        
        <br/>
        <a class="btn btn-primary" href="/selectuser">Back</a>
         <input type="submit" class="btn btn-primary" value="Login"  />
         </br>
         <c:if test="${not empty errorMessage}">
  			  <div class="alert alert-danger">
       				 ${errorMessage}
   				 </div>
		</c:if>
           		
        </div>
       
       
    </form>



</body>
</html>