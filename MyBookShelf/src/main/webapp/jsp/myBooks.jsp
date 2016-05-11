<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/8/16
  Time: 10:05 AM
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

<h1>${userName}'s Books</h1>

<table>
  <thead>
  <tr>
    <th>Title</th>
    <th>Author</th>
    <th>Notes</th>
    <th>Recommended</th>
    <th>Date Added</th>
  </tr>
  </thead>
  <tbody>

  <c:forEach items="${myBooks}" var="books">
      <tr>
        <td><a href="/goto-book?bookID=${books[0]}">${books[1]}</a></td>
        <td>${books[2]}</td>
        <td>${books[3]}</td>
        <td>${books[4]}</td>
        <td>${books[5]}</td>
        <td>${books[0]}</td>
      </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
