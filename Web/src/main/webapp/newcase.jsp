<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>NewCases</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="Front">
<aside>
    <jsp:include page="aside.jsp"/>
</aside>
<header style="margin-left: 15%;">
    <jsp:include page="header.jsp"/>
</header>
<main class="mainframe">
<c:if test="${thisuser.getUserActive() == 1}">
    <div class="Form" style="height: 47%; padding: 1.5rem!important;">
        <h2 class="FrontBlue" style="text-align: center;">New Case</h2>
        <form method="POST" action="/newcase/" class="MyGrid">
            <div>
                <label class="FrontBlue" style="font-size: 20px;">Case title: </label>
                <input style="font-size: 20px;" required name="caseTitle" class="MyInput" required/>
            </div>
            <div>
                <c:if test="${resultlabel != null}">
                    <c:if test="${userIdForLeader == null}">
                <Label class="FrontBlue" style="font-size: 20px;">Label: </label>
                    <select id="selectLabel" name="selectLabel" class="MyInput">
                        <option value="none">None</option>
                        <c:forEach items="${resultlabel}" var="result">
                        <option value="${result.getLabelId()}"  class="Label" style="background-color:${result.getLabelColor()}; "><c:out value="${result.getLabelTitle()}"/></option>
                        </c:forEach>
                    </select>
                    </c:if>
                    <c:if test="${userIdForLeader != null}">
                        <Label class="FrontBlue" style="font-size: 20px;">This task already has a label :)</label>
                    </c:if>
                </c:if>
            </div>
            <div><label class="FrontBlue" style="font-size: 20px;">Case due date: </label>
                <input required type="datetime-local" name="caseDueDate" class="MyInput" style="font-size: 20px;"/>
            </div>
            <div>
                <label class="FrontBlue" style="font-size: 20px;"> Case description: </label>
                <input style="font-size: 20px;" name="caseDescription" class="MyInput"/>
            </div>
            <div>
                <label class="FrontBlue" style="font-size: 20px;">Preparation time: </label>
                <input required style="font-size: 20px;" name="preparationTime" class="MyInput" type="time" value="00:00"/>
            </div>
            <div></div>
            <button type="submit" class="SubmitButton" style="margin-left:50% ;">Create a new case</button>
            <button type="reset" class="ResetButton">Reset</button>
        </form>
    </div>
    <div style="margin:3% auto;">
        <p style="color:#de4966"><c:out value="${message}"></c:out></p>
   </div>
</c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p style="color:#de4966">You've been banned</p>
    </c:if>
    <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
