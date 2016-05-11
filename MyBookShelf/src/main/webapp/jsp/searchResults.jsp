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
  <c:import url="head-tag.jsp" />
</head>
<body>

<c:import url="header.jsp" />

<c:import url="image.jsp" />

<br />
<c:choose>
  <c:when test="${searchType.equals('author')}">

    <c:if test="${searchResults.isEmpty()}">
      <h3>There were no books by that author</h3>
      <h3><a href="warArchive/index.jsp">Search Again</a></h3>
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
            <tr>
              <td><a href="/warArchive/goto-book?bookID=${result.get(0)}">${result.get(1)}</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
  </c:when>
  <c:otherwise>

    <c:if test="${searchResults.isEmpty()}">
      <h3>There were no books that match that title</h3>
      <h3><a href="/warArchive/index.jsp">Search Again</a></h3>
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
            <tr>
              <td><a href="warArchive/goto-book?bookID=${maps.get(0)}">
                  ${maps.get(1)}</a></td>
              <td>${maps.get(2)}</td>
            </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:if>
  </c:otherwise>
</c:choose>


</body>
</html>
