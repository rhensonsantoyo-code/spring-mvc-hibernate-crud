<html>
<head><meta charset="UTF-8"><title>Edit User</title></head>
<body>
<h2>Edit User</h2>

<form action="${pageContext.request.contextPath}/users/update" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    First Name: <label>
    <input type="text" name="firstName" value="${user.firstName}" required/>
</label><br/><br/>
    Last Name: <label>
    <input type="text" name="lastName" value="${user.lastName}" required/>
</label><br/><br/>
    Email: <label>
    <input type="email" name="email" value="${user.email}" required/>
</label><br/><br/>
    <input type="submit" value="Update"/>
</form>
<br/>
<a href="${pageContext.request.contextPath}/users">Back</a>
</body>
</html>
