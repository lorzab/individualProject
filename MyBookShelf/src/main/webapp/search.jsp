<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/1/16
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Bookshelf</title>
</head>
<body>

<h1>Search</h1>
<!-- Have if for if they come from index have search type already and show that -->


<!-- if from signed in screen search give search options -->
<form>
    <label>Search By:</label>
    <select name="searchBy">
        <option value="genre">Genre</option>
        <option value="title">Title</option>
        <option value="author">Author</option>
    </select>
    <input type="button" value="Enter" />
</form>
</body>
</html>
