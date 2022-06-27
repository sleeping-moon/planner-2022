<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.Case" %>
<%@ page import="java.util.List" %>
<%@ page import="static Dao.DataStorage.ValidateDate" %>
<%@ page import="Model.Label" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 25.03.2022
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>AllCases</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#caseTable').DataTable();
        });
    </script>
    <link href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css" rel="stylesheet" />

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
        <div class="ButtonGrid">
            <div><h2 class="FrontPink" style="font-size: 34px" ><c:out value="${userName}"/> case</h2></div>
            <c:if test="${thisIsNotUserGroup }">
          <div class="btn-group" role="group" aria-label="Basic example">
            <form method="POST" action="/case">
            <input type="hidden" name="actionType" value="Filter">
            <button type="submit" class="btn btn-secondary" name="caseFilter" value="active">Active</button>
            <button type="submit" class="btn btn-secondary" name="caseFilter" value="allCases">All Cases</button>
            <button type="submit" class="btn btn-secondary" name="caseFilter" value="closed">Closed</button>
            </form>
          </div>
            </c:if>
        </div>
    <div>
        <div class="container md-3 mt-3">
            <table id="caseTable" class="display">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Date</th>
                    <th scope="col">Due Date</th>
                    <th scope="col">Preparation Time</th>
                    <th scope="col">Label</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <%
                    SimpleDateFormat simpleDateFormatforDueDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
                    SimpleDateFormat simpleDateFormatforTime = new SimpleDateFormat("HH:mm");
                    List<Case> result = (List<Case>)request.getAttribute("results");
                    List<Label> resultlabel = (List<Label>)request.getAttribute("resultlabel");
                    for(int i =0; i<result.size(); i++){%>
                <%if(result.get(i).getCaseActive() == 1){ %>
                <tr>
                    <td scope="row"><%=i+1%></td>
                    <td><%=result.get(i).getcaseTitle()%></td>
                    <td><%=result.get(i).getcaseDate()%></td>
                    <td><%=simpleDateFormatforDueDate.format(result.get(i).getcaseDueDate())%></td>
                    <td><%=simpleDateFormatforTime.format(result.get(i).getPreparationTime())%></td>
                    <td>
                        <%if(resultlabel.get(i) != null){ %>
                        <button class="Label" style="background: <%=resultlabel.get(i).getLabelColor()%>"><%=resultlabel.get(i).getLabelTitle()%></button>
                        <%}%>
                    </td>
                    <td>
                    <c:if test="${thisIsNotUserGroup }">
                    <form method="POST" action="/mainframe"  style="width: 18px; margin-right: 0;">
                        <input type="hidden" name="actionType" value="completeTheTask">
                        <button class="DeleteUserFromGroup" name="DoneTaskButton" value="<%=result.get(i).getcaseNumber()%>">
                            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M23 12L20.56 9.21L20.9 5.52L17.29 4.7L15.4 1.5L12 2.96L8.6 1.5L6.71 4.69L3.1 5.5L3.44 9.2L1 12L3.44 14.79L3.1 18.49L6.71 19.31L8.6 22.5L12 21.03L15.4 22.49L17.29 19.3L20.9 18.48L20.56 14.79L23 12ZM10.09 16.72L6.29 12.91L7.77 11.43L10.09 13.76L15.94 7.89L17.42 9.37L10.09 16.72Z" fill="#97D7AA"/>
                            </svg>
                        </button>
                    </form>
                    </c:if>
                    </td>
                        <td>
                            <c:if test="${thisIsNotUserGroup }">
                            <%if(resultlabel.get(i) == null || resultlabel.get(i).getGroupTask()!= 1){ %>
                            <form method="POST" action="/deletecase">
                                <input type="hidden" name="actionType" value="choice_of_case">
                                <button type="submit"  name="caseNumber" class="DeleteUserFromGroup" value="<%=result.get(i).getcaseNumber()%>">
                                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z"
                                              fill="#C4C4C4"/>
                                    </svg>
                                </button>
                            </form>
                            <%}%>
                            </c:if>
                            <c:if test="${thisIsNotUserGroup == false}">
                                <c:if test="${result.get(i).getUserId() !=  thisuser.getuserId()}">
                                <form method="POST" action="/deletecase">
                                    <input type="hidden" name="actionType" value="choice_of_case">
                                    <button type="submit"  name="caseNumber" class="DeleteUserFromGroup" value="<%=result.get(i).getcaseNumber()%>">
                                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z"
                                                  fill="#C4C4C4"/>
                                        </svg>
                                    </button>
                                </form>
                                </c:if>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${thisIsNotUserGroup }">
                            <%if(resultlabel.get(i) == null || resultlabel.get(i).getGroupTask()!= 1){ %>
                                <form method="POST" action="/updatecase" >
                                <input type="hidden" name="actionType" value="choice_of_case">
                                <button class="DeleteUserFromGroup" type="submit"  name="caseNumber" value="<%=result.get(i).getcaseNumber()%>">
                                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M3 17.25V21H6.75L17.81 9.94L14.06 6.19L3 17.25ZM20.71 7.04C21.1 6.65 21.1 6.02 20.71 5.63L18.37 3.29C17.98 2.9 17.35 2.9 16.96 3.29L15.13 5.12L18.88 8.87L20.71 7.04V7.04Z" fill="#4A538B"/>
                                    </svg>
                                </button>
                                </form>
                                <%}%>
                            </c:if>
                            <c:if test="${thisIsNotUserGroup == false}">
                                <c:if test="${result.get(i).getUserId() !=  thisuser.getuserId()}">
                                <form method="POST" action="/updatecase" >
                                <input type="hidden" name="actionType" value="choice_of_case">
                                <button class="DeleteUserFromGroup" type="submit"  name="caseNumber" value="<%=result.get(i).getcaseNumber()%>">
                                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M3 17.25V21H6.75L17.81 9.94L14.06 6.19L3 17.25ZM20.71 7.04C21.1 6.65 21.1 6.02 20.71 5.63L18.37 3.29C17.98 2.9 17.35 2.9 16.96 3.29L15.13 5.12L18.88 8.87L20.71 7.04V7.04Z" fill="#4A538B"/>
                                    </svg>
                                </button>
                            </form>
                            </c:if>
                            </c:if>
                        </td>
                </tr>
                <%}%>
                <%if(result.get(i).getCaseActive() == 0){ %>
                <tr style="color: #C4C4C4">
                    <td scope="row"><%=i+1%></td>
                    <td><%=result.get(i).getcaseTitle()%></td>
                    <td><%=result.get(i).getcaseDate()%></td>
                    <td><%=simpleDateFormatforDueDate.format(result.get(i).getcaseDueDate())%></td>
                    <td><%=result.get(i).getcaseDescription()%></td>
                    <td><%if(resultlabel.get(i) != null){ %>
                        <button class="Label" style="background: <%=resultlabel.get(i).getLabelColor()%>"><%=resultlabel.get(i).getLabelTitle()%></button>
                        <%}%></td>
                    <td>
                        <c:if test="${thisIsNotUserGroup }">
                        <form method="POST" action="/deletecase">
                            <input type="hidden" name="actionType" value="choice_of_case">
                            <button type="submit"  name="caseNumber" class="DeleteUserFromGroup" value="<%=result.get(i).getcaseNumber()%>">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z"
                                          fill="#C4C4C4"/>
                                </svg>
                            </button>
                        </form>
                    </td>
                    <td> </td>
                    </c:if>
                </tr>
                <%}%>
                <%}%>
                </tbody>
            </table>
        </div>
        <c:if test="${thisIsNotUserGroup}">
        <button class="ResetButton" style="width: 30%"><a  style="text-decoration: none;" href="/newcase/">New Case</a></button>
        </c:if>
        <c:if test="${thisIsNotUserGroup == false}">
        <form method="POST" action="/group">
            <input type="hidden" name="actionType" value="formAddTaskForUser">
            <button type="submit" name="addTaskForUser" style="width: 30%" value="${userId}" class="AddTaskButton">add task</button>
        </form>
        </c:if>
    </div>
    </c:if>
    <c:if test="${thisuser.getUserActive() == 0}">
        <p>You've been banned</p>
    </c:if>
    <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
