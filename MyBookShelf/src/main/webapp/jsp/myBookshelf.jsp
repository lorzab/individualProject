<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/1/16
  Time: 8:20 PM
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

<h1>${userName}'s Bookshelf</h1>

<h2>${quoteOfDay}</h2>
<h4>-${quoteAuthor}</h4>

<h3><a href="/goto-my-books">My Books</a></h3>
<h3><a href="/goto-my-reading-list">Reading List</a></h3>
<h3><a href="/goto-get-recommended-books">Get Recommended Books</a></h3>
<h3><a href="../index.jsp">Search Shelves</a></h3>
<h3><a href="/goto-my-add-book">Add Book</a></h3>
<h3><a href="/goto-get-random-book">Get Random Book Suggestion</a></h3>


</body>
</html>
