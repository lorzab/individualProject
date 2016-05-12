<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/10/16
  Time: 8:05 PM
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

<h1>Delete Review</h1>
<form action="/goto-reviews-deleted" method="GET">
<table>
  <tr>
    <th>Review ID</th>
    <th>Review</th>
    <th>Delete</th>
  </tr>

  <c:forEach items="${reviewToModerate}" var="review">
    <tr>
      <td>${review.get(0)}</td>
      <td>${review.get(1)}</td>
      <td><input type="checkbox" value="${review.get(0)}" name="${review.get(0)}" /></td>
      <td><input type="submit" name="submit" value="delete" /></td>
    </tr>
  </c:forEach>
</table>
</form>
</body>
</html>
