<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="in-servlet">
    Login: <input name="login" />
    <br><br>
    Code: <input name="code"  />
    <input type="submit" value="Войти" />

</form>
</body>
</html>