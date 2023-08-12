<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.06.2023
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOG vera molodec !</title>
</head>
<body>

<p>${message}</p>

<form action="hello-servlet" method="post">
    <input type="text" name="group"/>
    <input type="text" name="course"/>
    <input type="submit" value="SUBMIT"/>
</form>
<form action="hello-servlet" method="get">
    <input type="submit" value="DELETE"/>
</form>

</body>
</html>
