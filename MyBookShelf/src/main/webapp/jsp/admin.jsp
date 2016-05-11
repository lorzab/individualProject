<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 2/17/16
  Time: 8:49 PM
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
<h1>Admin Page</h1>
<ul>
  <h3><a href="warArchive/index.jsp">Take me to the Search Page</a></h3>
  <h3><a href="warArchive/goto-approve-books">Approve Books</a></h3>
  <h3><a href="warArchive/goto-delete-reviews">Delete Reviews</a></h3>
</ul>
</body>
</html>
