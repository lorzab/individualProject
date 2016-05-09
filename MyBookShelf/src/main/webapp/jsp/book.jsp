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
    <title>${book}</title>
</head>
<body>

<c:if test="${userName == null}">
  <h3>You must log in to see or add books to your reading list</h3>
</c:if>

<h1>${book}</h1>
ID ${bookId} <br />
Author: ${author} <br />
ISBN ${isbn} <br />
Recommencation Percentage ${recommencationPercentage} <br />

<c:if test="${userName != null}">
  <c:if test="${!hasReadBook.equals(true)}">
    <form action="../goto-book-changed-wishlist" method="GET">

    <c:if test="${onWishList == 'false'}">
      Add to reading list <input type="checkbox" name="wishList" value="yes" />
    </c:if>
    <c:if test="${onWishList == 'true'}">
      Remove from reading list <input type="checkbox" name="wishList" value="no" />
    </c:if>
    <input type="submit" name="submit" value="submit">
      </form>
  </c:if>
    <c:if test="${hasReadBook.equals(true)}">
      You have read this book already
      <c:if test="${onWishList == 'false'}">
        <form action="../goto-book-changed-wishlist" method="GET">
          , want to read again?</br>
          Add to reading list <input type="checkbox" name="wishList" value="yes" />
          <input type="submit" name="submit" value="submit">
        </form>
      </c:if>
    </c:if>
</c:if>





</body>
</html>
