<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 5/7/16
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

</head>

<c:choose>
  <c:when test="${searchType.equals('author')}">

    <c:if test="${searchResults.isEmpty()}">
      <h3>There were no books by that author</h3>
      <h3><a href="index.jsp">Search Again</a></h3>
    </c:if>

    <c:if test="${!searchResults.isEmpty()}">
      <table>
        <thead>
          <tr>
            <th>${author}</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="result" items="${searchResults}">
            <c:forEach items="${result}" var="book">
              <tr>
                <td><a href="/goto-book?bookID=${book.key}">${book.value}"</a></td>
              </tr>
            </c:forEach>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </c:when>
  <c:otherwise>

    <c:if test="${searchResults.isEmpty()}">
      <h3>There were no books that match that title</h3>
      <h3><a href="index.jsp">Search Again</a></h3>
    </c:if>

    <c:if test="${!searchResults.isEmpty()}">
      <table>
        <thead>
        <tr>
          <th>Title</th>
          <th>Author</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${searchResults}" var="maps">
          <c:forEach items="${maps}" var="mapItem">
            <tr>
              <td><a href="/goto-book?bookID=${mapItem.key}">
                <c:forEach items="${mapItem.value}" var="book">
                  ${book.key}</a></td>
              <td>${book.value}</td>
              </c:forEach>
            </tr>
          </c:forEach>
        </c:forEach>
        </tbody>
      </table>
    </c:if>
  </c:otherwise>
</c:choose>


</body>
</html>
