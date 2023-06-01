<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Request Ticket</title>
</head>
<body>
    <h1>Create Request Ticket</h1>

    <form action="/request-tickets/create" method="POST">
        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId"><br><br>

        <label for="bookId">Book ID:</label>
        <input type="text" id="bookId" name="bookId"><br><br>

        <label for="requestDate">Request Date:</label>
        <input type="text" id="requestDate" name="requestDate"><br><br>

        
        <input type="submit" value="Create">
    </form>
</body>
</html>
