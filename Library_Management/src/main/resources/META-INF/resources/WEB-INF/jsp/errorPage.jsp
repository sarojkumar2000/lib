<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Tickets</title>
</head>
<body>
    <h1>Request Tickets</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Student ID</th>
            <th>Book ID</th>
            <th>Request Date</th>
            <th>Status</th>
        </tr>
        <c:forEach var="requestTicket" items="${requestTickets}">
            <tr>
                <td>${requestTicket.id}</td>
                <td>${requestTicket.studentId}</td>
                <td>${requestTicket.bookId}</td>
                <td>${requestTicket.requestDate}</td>
                <td>${requestTicket.status}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
