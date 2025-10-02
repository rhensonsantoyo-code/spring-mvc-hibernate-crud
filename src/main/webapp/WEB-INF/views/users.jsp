<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
</head>
<body>
<h2>All Users</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/users/edit/${user.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/users/delete/${user.id}"
                   onclick="return confirm('Delete user?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<br/>
<a href="${pageContext.request.contextPath}/users/add">Add New User</a>
</body>
</html>