<%--
  Created by IntelliJ IDEA.
  User: Vera Avdeeva
  Date: 03.06.2023
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>Name</th>
    <th>Phone</th>
    <th>Email</th>
  </tr>
  </thead>

  <tbody>
  <c:forEach step="1" varStatus="loopCounter" items="${airList}" var="airs">
    <tr>
      <td>
        <c:out value="${airs.getFrom()}" />
      </td>
      <td>
        <c:out value="${airs.getTo()}" />
      </td>
      <td>
        <c:out value="${airs.getTime()}" />
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
