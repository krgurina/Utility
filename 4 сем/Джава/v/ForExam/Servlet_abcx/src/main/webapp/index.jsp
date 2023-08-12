<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Сервлет abcx" %>
</h1>
<br/>
<%--//без слэша имя сервлета--%>
<form method="post" action="servletABCX">
    A: <input name="a" />
    <br><br>
    B: <input name="b"  />
    <br></br>
    C: <input name="c" />
    <br><br>
    X: <input name="x" />
    <br></br>

    <input type="submit" value="Submit" />

</form>

</body>
</html>