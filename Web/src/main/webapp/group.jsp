<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 03.04.2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group</title>
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
    <div id="zatemnenie">
        <div id="okno">
            <a href="#" class="close" style="margin-left: 95%;margin-top: -5%;">
                <svg width="14" height="14" viewBox="0 0 14 14" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M14 1.41L12.59 0L7 5.59L1.41 0L0 1.41L5.59 7L0 12.59L1.41 14L7 8.41L12.59 14L14 12.59L8.41 7L14 1.41Z" fill="#666666"/>
                </svg>
            </a>
            <p style="color: #FF9A8D;text-align: center; font-size: 20px; font-weight:bold;">Update Group</p>
            <form method="POST" action="/group">
                <input type="hidden" name="actionType" value="formUpdateGroup">
                <input name="changeLeader"  id="changeGroupLeader" value="<c:out value="${groupleader.getuserLogin()}"/>" class="form-control form-control-lg" style="border-radius: 2rem;" />
                <label class="form-label" for="changeGroupLeader" style="color:#4A538B ">Change Group Leader</label>
                <input name="changeTitle"  id="changeGroupTitle" value="<c:out value="${groupname}"/>" class="form-control form-control-lg" style="border-radius: 2rem;" />
                <label class="form-label" for="changeGroupTitle" style="color:#4A538B ">Change Group Title</label>
                <input name="changeDecription"  id="changeGroupDescription" value="<c:out value="${groupdescription}"/>" class="form-control form-control-lg" style="border-radius: 2rem;" />
                <label class="form-label" for="changeGroupDescription" style="color:#4A538B ">Change Group Description</label>
                <br><br>
                <div class="ButtonGrid">
                    <button class="SubmitButton" type="submit" name="Button"  value="submitButton" style="margin-left:65%;font-size: 20px;">save</button>
                    <button type="submit"class="ExitButton" name="Button" value="deleteButton">delete group</button>
                </div>
            </form>
        </div>
    </div>
    <div class="GroupGrid">
       <div class="FrontPink">
           <h2><c:out value="${groupname}"/></h2>
           <p style="text-align: center; font-size: 20px;"><c:out value="${groupdescription}"/></p>
       </div>
       <c:if test="${results != null }">
           <div class="GroupGrid">
               <div class="FrontPink" style="text-align: right;">leader</div>
               <div class="Form">
                   <div style ="display: grid; grid-template-columns: 40% 30% 20%; grid-column-gap: 1%;">
                       <div>
                       <img src="${pageContext.request.contextPath}/style/image/${groupleader.getUserPhoto()}"
                            class="bd-placeholder-img rounded-circle"  style="margin-left: auto; margin-right: auto" width="70" height="70" >
                       </div>
                       <div>
                       <p class="FrontBlue" style="text-align: center;font-size: 22px;"><c:out value="${groupleader.getuserName()}"/></p>
                       </div>
                       <div>
                       <c:if test="${groupleader.getuserId() == thisuser.getuserId()}">
                             <button class="DeleteUserFromGroup" style="height: 30px; margin-top: 1%;"><a href="#zatemnenie">
                                 <svg width="40" height="40" viewBox="0 0 43 43" fill="none" xmlns="http://www.w3.org/2000/svg">
                                     <path d="M37.9543 24.3584C38.04 23.7334 38.0829 23.0875 38.0829 22.4C38.0829 21.7334 38.04 21.0667 37.9329 20.4417L42.2807 17.15C42.6663 16.8584 42.7734 16.2959 42.5378 15.8792L38.4255 8.96252C38.1685 8.50419 37.6331 8.35836 37.1619 8.50419L32.043 10.5042C30.9721 9.71252 29.837 9.04586 28.5733 8.54586L27.8023 3.25419C27.7166 2.75419 27.2882 2.40002 26.7742 2.40002H18.5498C18.0357 2.40002 17.6288 2.75419 17.5431 3.25419L16.7721 8.54586C15.5084 9.04586 14.3519 9.73336 13.3024 10.5042L8.18351 8.50419C7.71232 8.33752 7.17687 8.50419 6.91986 8.96252L2.82905 15.8792C2.57203 16.3167 2.6577 16.8584 3.08606 17.15L7.43389 20.4417C7.3268 21.0667 7.24113 21.7542 7.24113 22.4C7.24113 23.0459 7.28396 23.7334 7.39105 24.3584L3.04323 27.65C2.6577 27.9417 2.55062 28.5042 2.78621 28.9209L6.89844 35.8375C7.15545 36.2959 7.6909 36.4417 8.16209 36.2959L13.281 34.2959C14.3519 35.0875 15.487 35.7542 16.7507 36.2542L17.5217 41.5459C17.6288 42.0459 18.0357 42.4 18.5498 42.4H26.7742C27.2882 42.4 27.7166 42.0459 27.7809 41.5459L28.5519 36.2542C29.8156 35.7542 30.9721 35.0875 32.0216 34.2959L37.1405 36.2959C37.6117 36.4625 38.1471 36.2959 38.4041 35.8375L42.5163 28.9209C42.7734 28.4625 42.6663 27.9417 42.2593 27.65L37.9543 24.3584ZM22.662 29.9C18.4213 29.9 14.9516 26.525 14.9516 22.4C14.9516 18.275 18.4213 14.9 22.662 14.9C26.9027 14.9 30.3724 18.275 30.3724 22.4C30.3724 26.525 26.9027 29.9 22.662 29.9Z" fill="#FF9A8D"/>
                                 </svg>
                             </a></button>
                                </c:if>
                       </div>
                   </div>
                        </div>
                    </div>
                    </div>
            </c:if>
        <c:if test="${results != null }">
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Login</th>
                    <th scope="col">Add task</th>
                    <th scope="col">View task</th>
                    <c:if test="${thisuser.getuserId()== groupleader.getuserId()}">
                        <th scope="col"></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${results}" var="result">
                    <tr>
                        <td scope="row"><c:out value="${result.getuserName()}"/></td>
                        <td><c:out value="${result.getuserLogin()}"/></td>
                        <td>
                            <c:if test="${groupleader.getuserId() == thisuser.getuserId() || thisuser.getuserId() == result.getuserId()}">
                            <form method="POST" action="/group">
                                <input type="hidden" name="actionType" value="formAddTaskForUser">
                            <button type="submit" name="addTaskForUser" value="${result.getuserId()}" class="AddTaskButton">add task</button>
                            </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${groupleader.getuserId() == thisuser.getuserId() || thisuser.getuserId() == result.getuserId() }">
                            <form method="POST" action="/group">
                                <input type="hidden" name="actionType" value="formViewTaskForUser">
                            <button type="submit" name="viewTaskForUser" value="${result.getuserId()}" class="ViewTaskButton">view</button>
                            </form>
                            </c:if>
                        </td>
                        <c:if test="${groupleader.getuserId() == thisuser.getuserId() || thisuser.getuserId() == result.getuserId() }">
                            <td>
                                <form method="POST" action="/group">
                                    <input type="hidden" name="actionType" value="formDeleteUserFromGroup">
                                    <button type="submit" value="${result.getuserId()}" name="deletelogin" class="DeleteUserFromGroup">
                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z"
                                                  fill="#C4C4C4"/>
                                        </svg>
                                    </button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    <c:if test="${groupleader.getuserId() == thisuser.getuserId()}">
    <div class="Form">
        <form method="POST" action="/group">
            <input type="hidden" name="actionType" value="formAddUserFromGroup">
            <label class="FrontBlue">Enter user login</label>
            <input style="font-size: 20px;" name="addUser" class="MyInput"required />
            <button type="submit"  class="ResetButton">Add User</button>
        </form>
    </div>
    </c:if>
    </c:if>
    <p style="color:#de4966"><c:out value="${message}"/></p>
    <c:if test="${thisGroup.getGroupInt() == 0}">
        <p style="color:#de4966">You are not in a group(((</p></c:if>
    <jsp:include page="footer.jsp"/>
</c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p style="color:#de4966">You've been banned</p>
    </c:if>
</main>
</body>
</html>
