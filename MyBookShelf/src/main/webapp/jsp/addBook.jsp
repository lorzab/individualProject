<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/1/16
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Bookshelf</title>
</head>
<body>

<h1>Add Book</h1>

<form>
    <label for="title">Title:</label>
    <input type="text" id="title" />

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" />

    <label for="author">Author:</label>
    <input type="text" id="author" />

    <label>Recommend:</label>
    <input type="radio" id="ratingYes" name="rating" value="Yes" />
    <input type="radio" id="ratingNo" name="rating" value="No" />

    <label for="notes">Notes:</label>
    <input type="textarea" id="notes" />

    <input type="button" id="add" value="add" />
    <input type="button" id="cancle" value="cancle" />
</form>

</body>
</html>
