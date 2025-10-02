<html>
<head><meta charset="UTF-8"><title>Add User</title></head>
<body>
<h2>Add New User</h2>

<form action="${pageContext.request.contextPath}/users/save" method="post">
    First Name: <label>
    <input type="text" name="firstName" required/>
</label><br/><br/>
    Last Name: <label>
    <input type="text" name="lastName" required/>
</label><br/><br/>
    Email: <label>
    <input type="email" name="email" required/>
</label><br/><br/>
    <input type="submit" value="Save"/>
</form>
<br/>
<a href="${pageContext.request.contextPath}/users">Back</a>
</body>
</html>