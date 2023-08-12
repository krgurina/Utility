<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Сервлет с доПост и доГет" %>
</h1>
<br/>
<form method="get" action="hello-servlet">
    <input type="submit" value="doGet" />
</form>
<form method="post" action="hello-servlet">
    <input type="submit" value="doPost" />
</form>
<h3>Cookie</h3>


<p>${cookie.protocol.value}</p>
</body>
</html>