<%--
  Created by IntelliJ IDEA.
  com.lorabahr.bookshelf.entity.User: Lora
  Date: 2/2/16
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <c:import url="jsp/head-tag.jsp" />
</head>
<body>

<c:import url="jsp/header.jsp" />

<c:import url="jsp/image.jsp" />

<br />

<h1><a href="/goto-search?searchType=title">Search by Title</a></h1>
<h1><a href="/goto-search?searchType=author">Search by Author</a></h1>
<h1><a href="/goto-search?searchType=allBooks">Go to all Books</a></h1>


</body>
</html>