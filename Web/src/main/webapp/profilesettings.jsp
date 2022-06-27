<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 16.04.2022
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProfileSettings</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- JavaScript -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function() {
            $('#caseTable').DataTable();
        });
    </script>
    <link href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" rel="stylesheet" />
</head>
<body>
<aside>
    <jsp:include page="aside.jsp"/>
</aside>
<header style="margin-left: 15%;">
    <jsp:include page="header.jsp"/>
</header>
<main class="mainframe">
    <div  class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <button  class="close" data-dismiss="modal" aria-label="Close" style="margin-left: 94%;">
                    <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M14 1.41L12.59 0L7 5.59L1.41 0L0 1.41L5.59 7L0 12.59L1.41 14L7 8.41L12.59 14L14 12.59L8.41 7L14 1.41Z" fill="#666666"/>
                    </svg>
                </button>
                <p style="color: #FF9A8D; text-align: center; font-size: 20px; font-weight:bold;">Choose your avatar</p>
                <form method="POST" action="/profilesettings">
                    <div class="modal-body" style="display: grid; grid-template-columns: 30% 30% 30%; grid-column-gap: 3%;
            grid-row-gap: 3%;">
                        <button type="submit" value="updateAvatar1" name="Button"  class="DeleteUserFromGroup">
                            <img src="${pageContext.request.contextPath}/style/image/2a20ebb683f925f1882d62e582b14ee8.jpg"  class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100" >
                        </button>
                        <button type="submit" value="updateAvatar2" name="Button"  class="DeleteUserFromGroup">
                        <img src="${pageContext.request.contextPath}/style/image/32a5d1422e2ad0282ebdc358bf6e76ab.jpg"  class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100" >
                        </button>
                        <button type="submit" value="updateAvatar3" name="Button"  class="DeleteUserFromGroup">
                        <img src="${pageContext.request.contextPath}/style/image/463d58d9119021ac7d043ee1897b6213.jpg"  class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100" >
                        </button>
                        <button type="submit" value="updateAvatar4" name="Button"  class="DeleteUserFromGroup">
                            <img src="${pageContext.request.contextPath}/style/image/c772867cd1988411690dac3bc5aea8ee.jpg" class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100" >
                        </button>
                        <button type="submit" value="updateAvatar5" name="Button"  class="DeleteUserFromGroup">
                            <img src="${pageContext.request.contextPath}/style/image/ed3f1938fcc1af63bc02e5c55ad943bb.jpg" class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100" >
                        </button>
                        <button type="submit" value="updateAvatar6" name="Button"  class="DeleteUserFromGroup">
                            <img src="${pageContext.request.contextPath}/style/image/no_avatar.jpg" class="bd-placeholder-img rounded-circle"
                             style="display: block;margin-left: auto; margin-right: auto" width="100" height="100">
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div style="margin: 0 25%">
        <form method="POST" action="/profilesettings">
            <div class ="ButtonGrid">
                <div><img type="button" src="${pageContext.request.contextPath}/style/image/${thisuser.getUserPhoto()}" alt=":(((" data-toggle="modal" data-target="#exampleModal"
                          style="border-radius: 100px; width: 197px;height: 197px;"/></div>
                <div><p style="text-align: center; front-size: 35;" class="FrontPink">${thisuser.getuserName()}</p></div>
            </div>
        <hr style="border-color: #C4C4C4">
        <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 24px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Name</p>
        <div style="margin-left: 10%; margin-right: 5%;">
            <input name="userName" maxlength="50" class="form-control form-control-lg"
                   style="border-radius: 2rem; background-color: #E5E5E5; width: 100%; height: 34px;border: none;"
                   value ="${thisuser.getuserName()}"/>
        </div>

        <hr style="border-color: #C4C4C4">
        <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 24px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Login</p>
        <div style="margin-left: 10%; margin-right: 5%;">
            <input name="userLogin" maxlength="40" class="form-control form-control-lg"
                   style="border-radius: 2rem; background-color: #E5E5E5; width: 100%; height: 34px;border: none;"
                   value ="${thisuser.getuserLogin()}" />
        </div>

            <hr style="border-color: #C4C4C4">
            <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 24px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Old Password</p>
            <div style="margin-left: 10%; margin-right: 5%;">
                <input name="userOldPassword"  type="password" class="form-control form-control-lg"
                       style="border-radius: 2rem; background-color: #E5E5E5; width: 100%; height: 34px;border: none;" />
            </div>

        <hr style="border-color: #C4C4C4">
        <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 24px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Password</p>
        <div style="margin-left: 10%; margin-right: 5%;">
            <input name="userPassword" maxlength="40" type="password" class="form-control form-control-lg"
                   style="border-radius: 2rem; background-color: #E5E5E5; width: 100%; height: 34px;border: none;" />
        </div>

        <hr style="border-color: #C4C4C4">
        <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 24px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Repeat Password</p>
        <div style="margin-left: 10%; margin-right: 5%;">
            <input name="userRepeatPassword" maxlength="40" type="password"  class="form-control form-control-lg"
                   style="border-radius: 2rem; background-color: #E5E5E5; width: 100%; height: 34px;border: none;" />
        </div>
            <br>
                <p style="color:#de49dc"><c:out value="${message}"/></p>
            <br><br>
            <div class ="ButtonGrid">
                <div><button value="save" name="Button" type="submit"  class="SubmitButton" style="width: 100%; font-size: 24px;">save</button></div>
                <div><button value="getOutOfAllGroups" name="Button" type="submit" class="ResetButton" style="width: 100%; font-size: 24px;"> get out of all groups</button></div>
                <div><button value="deleteProfile" name="Button" type="submit" class="ExitButton" style="width: 100%; font-size: 24px;">delete profile</button></div>
                <div><button value="deleteAllMyCases" name="Button" type="submit" class="ResetButton" style="width: 100%; font-size: 24px;"> delete all my cases</button></div>
            </div>
        </form>
    </div>
    <hr style="border-color: #C4C4C4">
    <div style="margin: 0 25%">
        <p style="font-family: 'Inter';font-style: normal;
                  font-weight: 400;font-size: 26px;line-height: 29px;color: #C4C4C4; margin-top: -3%">Label</p>
        <div>
            <table id="caseTable" class="table">
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
                    <input style="font-size: 20px; width: 90%;" id= "labeltitle" required name="labelTitle" class="MyInput"/>
                </div>

                <div>
                    <label class="form-label" for="labelcolor" style="color:#4A538B ">Label Color</label><br>
                    <input style="font-size: 20px;" id ="labelcolor"type="color" required name="labelColor" class="MyInput"/>

                </div>
                <button type="submit" class="SubmitButton" style="margin-left:50% ;">Create a new label</button>
                <button type="reset" class="ResetButton">Reset</button>
            </form>
        </div>
    </div>
</main>
<footer class="mainframe">
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
