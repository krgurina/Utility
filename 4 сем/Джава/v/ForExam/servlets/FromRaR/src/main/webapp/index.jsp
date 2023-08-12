<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>+
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "вера авдеева кидло" %>
</h1>
<br/>

<form method="post" action="hello-servlet">
    <input type="submit" value="Check"/>
</form>


<table>
    <thead>
    <tr>
        <th>Author</th>
        <th>Book_name</th>
        <th>Publication_year</th>
        <th>Pages</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach step="1" varStatus="loopCounter" items="${list}" var="item">
        <tr>
            <td>
                <c:out value="${item.getAuthor()}" />
            </td>
            <td>
                <c:out value="${item.getBook_name()}" />
            </td>
            <td>
                <c:out value="${item.getPublication_year()}" />
            </td>
            <td>
                <c:out value="${item.getPages()}" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>