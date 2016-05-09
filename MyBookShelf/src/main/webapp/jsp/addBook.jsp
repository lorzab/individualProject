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

<form action="/goto-my-book-added" method="GET">

    <label>Title:</label>
    <input type="text" name="title" />
    <br />

    <label>ISBN:</label>
    <input type="text" name="isbn" />
    <br />

    <label>Author:</label>
    <input type="text" name="author" />
    <br />

    <label>Recommend:</label>
    <label>Yes</label><input type="radio" name="rating" value="Yes" />
    <label>No</label><input type="radio" name="rating" value="No" />
    <br />

    <label>Notes:</label>
    <input type="textarea" name="notes" />
    <br />

    <input type="submit" id="submit" value="submit" />
    <a href="/goto-my-bookshelf">Back</a>
</form>

</body>
</html>
