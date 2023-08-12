<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.06.2023
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vera lox</title>
</head>
<body>

<p>${message}</p>
<p>${like}</p>
<p>${dislike}</p>

<form method="post" action="hello-servlet">
    <input type="submit" value="Like"/>
</form>

<form method="get" action="hello-servlet">
    <input type="submit" value="Dislike"/>
</form>

</body>
</html>
