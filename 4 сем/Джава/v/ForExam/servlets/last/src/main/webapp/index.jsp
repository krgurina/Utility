<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<style>
    input {
        background: lightslategray;
        text-align: center;
        padding: 5px;
        width: 100px;
        border-radius: 10px;
    }
</style>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<form method="post" action="hello-servlet">
    <input type="submit" value="Post">
</form>
<p>${cookie.protocol.value}</p>
<form method="get" action="hello-servlet">
    <input type="submit" value="Get">
</form>

</body>
</html>