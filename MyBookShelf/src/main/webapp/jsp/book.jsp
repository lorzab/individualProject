<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/7/16
  Time: 6:29 PM
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
<c:if test="${userName == null}">
  <h3>You must log in to see or add books to your reading list</h3>
</c:if>

<h1>${book}</h1>
<h3>ID ${bookId} </h3>
<h3>Author: ${author}</h3>
<h3>ISBN ${isbn}</h3>
<h3>Recommencation Percentage ${recommencationPercentage}</h3>

<c:if test="${userName != null}">
  <c:if test="${!hasReadBook.equals(true)}">
    <form action="../goto-book-changed-wishlist" method="GET">

    <c:if test="${onWishList == 'false'}">
      <h3>Add to reading list</h3> <input type="checkbox" name="wishList" value="yes" />
    </c:if>
    <c:if test="${onWishList == 'true'}">
      <h3>Remove from reading list</h3> <input type="checkbox" name="wishList" value="no" />
    </c:if>
    <input type="submit" name="submit" value="submit">
      </form>
  </c:if>
    <c:if test="${hasReadBook.equals(true)}">
      <h3>You have read this book already, want to read again?</h3>
      <c:if test="${onWishList == 'false'}">
        <form action="../goto-book-changed-wishlist" method="GET">
          <h3>Add to reading list</h3> <input type="checkbox" name="wishList" value="yes" />
          <input type="submit" name="submit" value="submit">
        </form>
      </c:if>
    </c:if>
</c:if>





</body>
</html>
