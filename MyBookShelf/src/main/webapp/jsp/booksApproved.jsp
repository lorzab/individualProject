<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/9/16
  Time: 8:43 PM
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

<h1>Books Approved</h1>

<table>
  <c:forEach items="${approvedBooks}" var="book">
    <tr>
      <td><a href="/goto-book?bookID=${book.get(0)}">${book.get(1)}</a></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
