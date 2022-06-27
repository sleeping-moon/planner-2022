<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Labels</title>
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
        <c:if test="${thisuser.getuserRole() != 3}">
        <h2 class="FrontPink" style="font-size: 34px" >My Labels</h2>
        </c:if>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Label</th>
                    <c:if test="${thisuser.getuserRole() == 3}">
                    <th scope="col">User Id</th>
                    </c:if>
                    <c:if test="${thisuser.getuserRole() != 3}">
                    <th scope="col"></th>
                    </c:if>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${results}" var="result">
                        <tr>
                            <td>
                                <button class="Label" style="background: <c:out value="${result.getLabelColor()}"/>;width: 30%"><c:out value="${result.getLabelTitle()}"/></button>
                            </td>
                            <td>
                                <c:if test="${thisuser.getuserRole() == 3}">
                                <c:if test="${result.getUserId() != 0}">
                                <c:out value="${result.getUserId()}"/>
                                </c:if>
                                <c:if test="${result.getUserId() == 0}">
                                    <p>For everyone</p>
                                </c:if>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${(result.getUserId() != 0)||(thisuser.getuserRole() == 3)}">
                                <form method="POST" action="/labels/">
                                    <input type="hidden" name="actionType" value="deleteLabels">
                                    <button type="submit" value="${result.getLabelId()}" name="deleteLabelsId" class="DeleteUserFromGroup">
                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z"
                                                  fill="#C4C4C4"/>
                                        </svg>
                                    </button>
                                </form>
                                </c:if>
                            </td>
                        </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="Form">
            <p class="FrontPink" style="text-align: center;">New Label</p>
            <form method="POST" action="/labels/" class="MyGrid">
                <input type="hidden" name="actionType" value="addNewLabel">
                <div>
                    <label class="form-label"  for="labeltitle" style="color:#4A538B ">Label Title</label>
                    <input style="font-size: 20px;" id= "labeltitle" required name="labelTitle" class="MyInput"/>
                </div>

                <div>
                    <label class="form-label" for="labelcolor" style="color:#4A538B ">Label Color</label>
                    <input style="font-size: 20px;" id ="labelcolor"type="color" required name="labelColor" class="MyInput"/>

                </div>
                <button type="submit" class="SubmitButton" style="margin-left:50% ;">Create a new label</button>
                <button type="reset" class="ResetButton">Reset</button>
            </form>
        </div>
        <p style="color:#de4966"><c:out value="${message}"></c:out></p>
    </c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p style="color:#de4966">You've been banned</p>
    </c:if>
</main>
<footer class="mainframe">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
