<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>${message}</h1>
<br/>
<form method="post" action="helloServlet">
    <input type="text" name="phone">
    <input type="text" name="nam">
    <input type="submit" value="go">
</form>

</body>
</html>