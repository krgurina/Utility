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
<a href="hello-servlet">Hello Servlet</a>

<form method="post" action="helloServlet">
    <input type="text" name="a"/>
    <input type="text" name="b"/>
    <input type="text" name="c"/>
    <input type="text" name="x"/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>