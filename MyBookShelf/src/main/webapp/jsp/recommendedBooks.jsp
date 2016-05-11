<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/8/16
  Time: 9:53 PM
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

<form action="/goto-recommended-books" method="GET">
<table>
  <thead>
  <tr>
    <th>Title</th>
    <th>Author</th>
    <th>Note</th>
    <th>Recommended Rating</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${recommendedBooks}" var="books">
      <tr>
        <td><a href="/goto-book?bookID=${books.get(0)}">${books.get(1)}</a></td>
        <td>${books.get(2)}</td>
        <td>${books.get(3)}</td>
        <td>${books.get(4)}</td>
      </tr>
  </c:forEach>
  </tbody>
</table>
</form>

</body>
</html>
