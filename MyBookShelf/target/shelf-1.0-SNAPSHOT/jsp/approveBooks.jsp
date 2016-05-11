<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/9/16
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="head-tag.jsp" />
</head>
<body>

<c:import url="header.jsp" />

<c:import url="image.jsp" />

<br />
<h1>Approve Books</h1>

<form action="/goto-approvedbooks" method="GET">
<table>
  <tr>
    <th>BookID</th>
    <th>Title</th>
    <th>Author</th>
    <th>Approve</th>
  </tr>

  <c:forEach items="${nonApproved}" var="book">
  <tr>
      <td>${book.get(0)}</td>
      <td>${book.get(1)}</td>
      <td>${book.get(2)}</td>
      <td><input type="checkbox" value="${book.get(0)}" name="${book.get(0)}" /></td>
      <td><input type="submit" name="submit" value="submit" /></td>
  </tr>
  </c:forEach>

</table>
</form>
</body>
</html>
