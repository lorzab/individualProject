<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/9/16
  Time: 10:17 PM
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
<h1>Get Random Book</h1>

<form action="warArchive/goto-random-book" method="GET">
  <input type="submit" name="submit" value="Get a Random Book Suggestion" />
</form>
</body>
</html>
