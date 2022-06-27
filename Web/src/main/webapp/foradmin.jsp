<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 05.04.2022
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#userTable').DataTable();
        });
    </script>
    <link href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" rel="stylesheet" />

</head>
<body>
<c:if test="${thisuser.getuserRole() == 3}">
<aside>
    <jsp:include page="aside.jsp"/>
</aside>
<header style="margin-left: 15%;">
    <jsp:include page="header.jsp"/>
</header>
<main class="mainframe">
    <div>
        <table class="display" id="userTable">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">User role</th>
                <th scope="col">Ban</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="result">
                <c:if test="${result.getuserRole() != 3}">
                <tr>
                    <td scope="row"><c:out value="${result.getuserId()}"/>
                    </td>
                    <td><c:out value="${result.getuserName()}"/>
                    </td>
                    <td><c:out value="${result.getuserLogin()}"/>
                    </td>
                    <td><c:out value="${result.getuserRole()}"/>
                    </td>
                    <td>
                        <form method="POST" action="/foradmin">
                            <input type="hidden" name="actionType" value="formUpdateUserActive">
                            <button type="submit" value="${result.getuserId()}" name="updateUserActive" class="DeleteUserFromGroup">
                                <c:if test="${result.getUserActive() == 1}">
                                <svg width="22" height="16" viewBox="0 0 21 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 4C12 1.79 10.21 0 8 0C5.79 0 4 1.79 4 4C4 6.21 5.79 8 8 8C10.21 8 12 6.21 12 4ZM15 6V8H21V6H15ZM0 14V16H16V14C16 11.34 10.67 10 8 10C5.33 10 0 11.34 0 14Z" fill="#DE4966"/>
                                </svg>
                                </c:if>
                                <c:if test="${result.getUserActive() == 0}">
                                    <svg width="22" height="16" viewBox="0 0 22 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M12 4C12 1.79 10.21 0 8 0C5.79 0 4 1.79 4 4C4 6.21 5.79 8 8 8C10.21 8 12 6.21 12 4ZM14 6V8H17V11H19V8H22V6H19V3H17V6H14ZM0 14V16H16V14C16 11.34 10.67 10 8 10C5.33 10 0 11.34 0 14Z" fill="#97D7AA"/>
                                    </svg>
                                </c:if>
                            </button>
                        </form>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="Form">
        <p class="FrontPink" style="text-align: center;">New Label</p>
        <form method="POST" action="/foradmin/" class="MyGrid">
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
<c:if test="${thisuser.getuserRole() != 3}">
    <jsp:include page="404error.jsp"/>
</c:if>
</main>
<footer class="mainframe">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
