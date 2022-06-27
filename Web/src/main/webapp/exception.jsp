<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 04.04.2022
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception</title>
    <link rel="stylesheet" href="/style/style.css">
</head>
<body>
<h2 align="center" class="FrontPink">Exception occurred while processing the request</h2>
<p align="center" class="FrontBlue">Type: <%= exception%></p>
<p align="center" class="FrontBlue">Message: <%= message %></p>
</body>
</html>
