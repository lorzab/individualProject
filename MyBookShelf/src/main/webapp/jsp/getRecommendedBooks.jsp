<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/8/16
  Time: 9:49 PM
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
<h1>Get Recommended Books</h1>

<form action="/goto-recommended-books" method="GET">

  <input type="submit" name="submit" value="Get Books" />

</form>

</body>
</html>
