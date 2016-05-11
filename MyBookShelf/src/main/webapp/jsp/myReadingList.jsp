<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/8/16
  Time: 8:31 PM
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
<h1>${userName}'s Books To Read</h1>

<c:if test="${myBooks.isEmpty()}">
  <h3>There are no books on your reading list currently, <a href="index.jsp">search</a> for books to read</h3>
</c:if>

<c:if test="${!myBooks.isEmpty()}">
  <table>
    <thead>
    <tr>
      <th>Title</th>
      <th>Author</th>
      <th>Date Added</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${myBooks}" var="books">
        <tr>
          <td><a href="/goto-book?bookID=${books[0]}">${books[1]}</a></td>
          <td>${books[2]}</td>
          <td>${books[5]}</td>
        </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>


</body>
</html>
