<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/1/16
  Time: 8:00 PM
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

<h1>Search</h1>

<form action="/goto-search-results" method="GET">


<c:choose>
    <c:when test="${searchType.equals('title')}">
        <h3>Title Search</h3>
        <label>Looking for the title: </label>
        <input type="text" name="titileSearch" />
    </c:when>
    <c:when test="${searchType.equals('author')}">
        <h3>Author Search</h3>
        <label>Searching for author: </label>
        <input type="text" name="authorSearch" />
    </c:when>
    <c:when test="${searchType.equals('allBooks')}">
        <h3>Search all books in the shelves</h3>

    </c:when>
    <c:otherwise>
        <h3><a href="/index.jsp">Go to Search Options</a></h3>
    </c:otherwise>
</c:choose>

    <input type="submit" name="submit" value="submit" />
</form>

</body>
</html>
