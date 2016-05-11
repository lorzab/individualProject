<%--
  Created by IntelliJ IDEA.
  User: Lora
  Date: 2/17/16
  Time: 8:42 PM
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

<h1><a href="/warArchive/index.jsp">Look at all Books</a></h1>

<h2>or</h2>

<h1>Sign in to your Bookshelf</h1>

<FORM ACTION="j_security_check" METHOD="POST">
  <TABLE>
    <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
    <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
    <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
  </TABLE>
</FORM>

</body>
</html>
