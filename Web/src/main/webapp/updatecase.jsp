<%@ page import="Model.Case" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Update</title>
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
        <p style="color:#de4966"><c:out value="${checksuccess }"/></p>
        <c:if test="${results == null }">
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
        <c:if test="${results != null }">
            <div class="Form">
                    <div><p style="color: #4A538B; font-size: 22px;">Case Title: <c:out value="${results.getcaseTitle()}"/></p></div>
                <div class="MyGrid">
                    <div><p style="color: #afaeae;">Case Date:
                            <c:out value="${results.getcaseDate()}"/>
                          </p>
                    </div>
                    <div><p style="color: #afaeae;">Case Due Date:
                           <c:out value="${simpleDateFormatforDueDate.format(results.getcaseDueDate())}"/>
                         </p>
                    </div>
                </div>
                <p style="color: #afaeae;">Preparation time:
                    <c:out value="${simpleDateFormatforTime.format(results.getPreparationTime())}"/>
                </p>
                <p style="color:  #afaeae;">Label :
                    <c:if test="${resultsCaseLabel != null }">
                        <button class="Label" style="width: 20%; background:${resultsCaseLabel.getLabelColor()}">
                            <c:out value="${resultsCaseLabel.getLabelTitle()}"/>
                        </button>
                    </c:if>

                </p>
                <p style="color: #4A538B;">Case description: <c:out value="${results.getcaseDescription()}"/></p>
                <c:if test="${results.getCaseActive() == 1}">
                    <p style="color: #97D7AA;">Case Active </p>
                </c:if>
                <c:if test="${results.getCaseActive() == 0}">
                    <p style="color:#de4966">Case is Closed</p>
                </c:if>
            </div>
            <br>
            <div class="Form" style="height: 45%">
            <h2 class="FrontBlue" style="text-align: center;">Update Case</h2>
            <form method="POST" action="/updatecase/" class="MyGrid">
                <div><label class="FrontBlue" style="font-size: 20px;">Case title: </label>
                    <input style="font-size: 20px;" required name="caseTitle" class="MyInput"
                           value="${results.getcaseTitle()}"/>
                </div>
                <div>
                    <c:if test="${resultAllLabels != null}">
                        <Label class="FrontBlue" style="font-size: 20px;">Label: </label>
                        <select id="selectLabel" name="selectLabel" class="MyInput">
                            <c:if test="${resultsCaseLabel == null}">
                                <option value="none">None</option>
                            </c:if>
                            <c:forEach items="${resultAllLabels}" var="result">
                                <option value="${result.getLabelId()}"  class="Label" style="background-color:${result.getLabelColor()}; ">
                                    <c:out value="${result.getLabelTitle()}"/>
                                </option>
                            </c:forEach>
                            <c:if test="${resultAllLabels.size() != 1 && resultsCaseLabel != null }">
                            <option value="none">None</option>
                            </c:if>
                        </select>
                    </c:if>
                </div>
                <div><label class="FrontBlue" style="font-size: 20px;">Case due date: </label>
                    <input required type="datetime-local" name="caseDueDate" class="MyInput" style="font-size: 20px;"
                           value="${dueDateInputFormat}"/>
                </div>
                <div><label class="FrontBlue" style="font-size: 20px;"> Case description: </label>
                    <input style="font-size: 20px;" name="caseDescription" class="MyInput"
                           value="${results.getcaseDescription()}"/>
                </div>
                <div>
                    <label class="FrontBlue" style="font-size: 20px;">Preparation time: </label>
                    <input required style="font-size: 20px;" name="preparationTime" class="MyInput" type="time"
                           value="${preparationTime}"/>
                </div>
                <div></div>
                <input type="hidden" name="actionType" value="parameter_change">
                <button type="submit" class="SubmitButton" style="margin-left:50% ;">Update case</button>
                <button type="reset" class="ResetButton">Reset</button>
            </form>
        </c:if>
        </div>
        <br>
    </c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p style="color:#de4966">You've been banned</p>
    </c:if>
    <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
