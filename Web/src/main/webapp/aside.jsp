<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 04.04.2022
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="SideBar">
    <br>
    <img src="${pageContext.request.contextPath}/style/image/${thisuser.getUserPhoto()}" class="bd-placeholder-img rounded-circle"
         style="display: block;margin-left: auto; margin-right: auto; margin-top: 2%" width="100" height="100">
    <p class="FrontPink">${thisuser.getuserName()}</p>
    <a  aria-current="page" href="/mainframe/">Home</a>
    <c:if test="${thisuser.getuserRole() == 3}">
        <a href="/foradmin/">For Admin</a>
    </c:if>
    <a href="/case/">Case</a>
    <a href="/groupselection/">Group</a>
    <c:if test="${thisuser.getuserRole() == 3}">
    <a href="/labels/">Label</a>
    </c:if>
    <a href="/profilesettings/">Profile Settings</a>
    <br><br>
    <div style="margin: 48% 0 0 17.5%;">
        <form method="POST" action="/logout">
            <button class="ExitButton" type="submit">Exit</button>
        </form>
    </div>
</nav>
</body>
</html>
