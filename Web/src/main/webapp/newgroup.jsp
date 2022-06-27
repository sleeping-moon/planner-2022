<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 09.04.2022
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NewGroup</title>
</head>
<body>
<aside>
    <jsp:include page="aside.jsp"/>
</aside>
<header style="margin-left: 15%;">
    <jsp:include page="header.jsp"/>
</header>
<main class="mainframe">
<c:if test="${thisuser.getUserActive() == 1}">
    <div class="Form">
        <h2 class="FrontBlue" style="text-align: center;">New Group</h2>
        <form method="POST" action="/newgroup/" class="MyGrid">
            <div>
                <label class="FrontBlue" style="font-size: 20px;">Group name: </label>
                <input style="font-size: 20px;" required name="groupName" class="MyInput"/>
            </div>
            <div>
                <label class="FrontBlue" style="font-size: 20px;"> Description: </label>
                <input style="font-size: 20px;"   name="groupDescription" class="MyInput"/>
            </div>
            <button type="submit" class="SubmitButton" style="margin-left:50% ;">Create a new group</button>
            <button type="reset" class="ResetButton">Reset</button>
        </form>
    </div>
</c:if>
</main>
<footer class="mainframe">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
