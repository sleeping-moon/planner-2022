<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Case" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>DeleteCase</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
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
        <p style="color:#de4966"> <c:out value="${checksuccess }"/></p>
    <c:if test="${resultsDelete == null }">
        <div class="Form">
            <form method="POST" action="/updatecase/" class="MyGrid Form">
                <label class="FrontBlue" style="font-size: 20px;">Case Number: </label>
                <input name="caseNumber" class="MyInput"/>
                <input type="hidden" name="actionType" value="choice_of_case">
                <button class="SubmitButton" type="submit" style="margin-left:50% ;">Search</button>
                <button type="reset" class="ResetButton">Reset</button>
            </form>
        </div>
    </c:if>
        <c:if test="${resultsDelete != null }">
            <div class="Form">
                    <div><p style="color: #4A538B; font-size: 22px;">Case Title: <c:out value="${resultsDelete.getcaseTitle()}"/></p></div>
                <div class="MyGrid">
                    <div><p style="color: #afaeae;">Case Date:
                        <c:out value="${resultsDelete.getcaseDate()}"/>
                    </p>
                    </div>
                    <div><p style="color: #afaeae;">Case Due Date:
                        <c:out value="${simpleDateFormatforDueDate.format(resultsDelete.getcaseDueDate())}"/>
                    </p>
                    </div>
                </div>
                <p style="color: #afaeae;">Preparation time:
                    <c:out value="${simpleDateFormatforTime.format(resultsDelete.getPreparationTime())}"/>
                </p>
                <p style="color:  #afaeae;">Label :
                    <c:if test="${resultsCaseLabel != null }">
                        <button class="Label" style="width: 20%; background:${resultsCaseLabel.getLabelColor()}">
                            <c:out value="${resultsCaseLabel.getLabelTitle()}"/>
                        </button>
                    </c:if>
                </p>
                <p style="color: #4A538B;">Case description: <c:out value="${resultsDelete.getcaseDescription()}"/></p>
                <c:if test="${resultsDelete.getCaseActive() == 1}">
                    <p style="color: #97D7AA;">Case Active </p>
                </c:if>
                <c:if test="${resultsDelete.getCaseActive() == 0}">
                    <p style="color:#de4966">Case is Closed</p>
                </c:if>
            </div>
            <br><br><br>
        <form method="POST" action="/deletecase/" class="Form">
            <p class="FrontBlue">Are you sure you want to delete the case? </p>
            <div class="MyGrid">
               <input type="hidden" name="actionType" value="delete_this_case">
                <div><button type="submit" name="solution" value="yes" class="SubmitButton">Yes</button></div>
                <div><button type="submit" name="solution" value="no" class="ResetButton">No</button></div>
            </div>
        </form>
    </c:if>
    </c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p style="color:#de4966">You've been banned</p>
    </c:if>
    <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
