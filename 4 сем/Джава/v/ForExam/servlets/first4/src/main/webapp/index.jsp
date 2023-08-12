<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<form method="post" action="hello-servlet">
    <input type="text" name="name"/>
    <input type="text" name="phone"/>
    <input type="text" name="email"/>
    <input type="submit" value="Get">
</form>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach step="1" varStatus="loopCounter" items="${examList}" var="exam">
        <tr>
            <td>
                <c:out value="${exam.getName()}" />
            </td>
            <td>
                <c:out value="${exam.getEmail()}" />
            </td>
            <td>
                <c:out value="${exam.getPhone()}" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>