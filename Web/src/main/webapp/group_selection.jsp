<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 08.04.2022
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GroupSelection</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Описание</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="result">
                <tr>
                    <td scope="row">
                        <form method="POST" action="/groupselection">
                            <input type="hidden" name="actionType" value="formGroupSelection">
                            <button type="submit" name="GroupSelection" value="${result.getGroupId()}" class="DeleteUserFromGroup GroupSelection">
                               <c:out value="${result.getGroupName()}"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="/groupselection">
                            <input type="hidden" name="actionType" value="formGroupSelection">
                            <button type="submit" name="GroupSelection" value="${result.getGroupId()}" class="DeleteUserFromGroup GroupSelection">
                        <c:out value="${result.getGroupDescription()}"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <button class="ResetButton" style="width: 30%"> <a  style="text-decoration: none;" href="/newgroup/">New Group +</a></button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p>You've been banned</p>
    </c:if>
    <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
