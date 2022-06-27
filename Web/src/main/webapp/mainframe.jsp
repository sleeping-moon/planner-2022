<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Model.Case" %>
<%@ page import="Model.Label" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
  <form method="POST" action="/mainframe">
      <input type="hidden" name="actionType" value="daySelection">
        <div class="look-calendar" style="margin: 0 10%;">
          <table id="calendar">
           <thead>
             <tr>
               <td><b style="color: #FF9A8D;">‹</b>
               <td colspan="5">
               <td><b style="color: #FF9A8D;">›</b>
             </tr>
             <tr class="dn">
                 <td>Mon<td>Tue<td>Wed<td>Thu<td>Fri<td>Sat<td>Sun
             </tr>
           </thead>
          <tbody></tbody>
    </table>
    <script src="/style/script.js"></script>
  </div>
  </form>
  <br><br><br>
  <h2 style="color: #FF9A8D;">Task for<c:out value="${taskForDate}"/></h2>
    <br>
    <div style="margin: 0 5% 0 10%;">
    <div style="display: grid;grid-template-columns: 22% 22% 22% 22%; grid-column-gap: 4%; grid-row-gap: 10%;" >
        <%
            SimpleDateFormat simpleDateFormatforDueDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
            List<Case> result = (List<Case>)request.getAttribute("results");
            List<Boolean> isAGroupTask = (List<Boolean>)request.getAttribute("isAGroupTask");
            List<String> resultcolor = (List<String>)request.getAttribute("resultcolor");
            for(int i =0; i<result.size(); i++){%>
        <%if(result.get(i).getCaseActive() == 1){%>
        <div class="card  mb-3" style="max-width: 18rem; border-color: <%=resultcolor.get(i)%>; border-radius: 15px; position:relative; ">
            <div class="card-header"><%=result.get(i).getcaseTitle()%></div>
            <div class="card-body " style="color: <%=resultcolor.get(i)%>;">
                <h5 class="card-title" style ="font-weight:bold; font-size: 20px"><%=simpleDateFormatforDueDate.format(result.get(i).getcaseDueDate())%></h5>
                <p class="card-text"><%=result.get(i).getcaseDescription()%></p>
                <div style="position:absolute; bottom: -5px; width: 100%;">
                    <div style="margin-left: 45%; display: grid; grid-template-columns: 30% 30% 36%;grid-column-gap: 0%;">
                        <div  style="width: 18px;margin-right: 0;">
                            <form method="POST" action="/mainframe"  style="width: 18px; margin-right: 0;">
                                <input type="hidden" name="actionType" value="completeTheTask">
                                  <button class="DeleteUserFromGroup" name="DoneTaskButton" value="<%=result.get(i).getcaseNumber()%>">
                                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                      <path d="M23 12L20.56 9.21L20.9 5.52L17.29 4.7L15.4 1.5L12 2.96L8.6 1.5L6.71 4.69L3.1 5.5L3.44 9.2L1 12L3.44 14.79L3.1 18.49L6.71 19.31L8.6 22.5L12 21.03L15.4 22.49L17.29 19.3L20.9 18.48L20.56 14.79L23 12ZM10.09 16.72L6.29 12.91L7.77 11.43L10.09 13.76L15.94 7.89L17.42 9.37L10.09 16.72Z" fill="#97D7AA"/>
                                     </svg>
                                  </button>
                            </form>
                        </div>
                        <% if(isAGroupTask.get(i) == false){ %>
                        <div style="width: 18px;margin-right: 0;">
                        <form method="POST" action="/updatecase"  style="width: 18px; margin-right: 0;">
                            <input type="hidden" name="actionType" value="choice_of_case">
                             <button class="DeleteUserFromGroup" type="submit"  name="caseNumber" value="<%=result.get(i).getcaseNumber()%>">
                               <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                 <path d="M3 17.25V21H6.75L17.81 9.94L14.06 6.19L3 17.25ZM20.71 7.04C21.1 6.65 21.1 6.02 20.71 5.63L18.37 3.29C17.98 2.9 17.35 2.9 16.96 3.29L15.13 5.12L18.88 8.87L20.71 7.04V7.04Z" fill="#4A538B"/>
                               </svg>
                             </button>
                        </form>
                        </div>
                        <div style="width: 18px;margin-right: 0;">
                        <form method="POST" action="/deletecase" style="width: 18px; margin-right: 0;">
                            <input type="hidden" name="actionType" value="choice_of_case">
                              <button class="DeleteUserFromGroup" type="submit" name="caseNumber" value="<%=result.get(i).getcaseNumber()%>">
                                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                  <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z" fill="#C4C4C4"/>
                                </svg>
                               </button>
                        </form>
                    </div>
                        <% }%>
                    </div>
                </div>
            </div>
        </div>
        <%}%>
        <%if(result.get(i).getCaseActive() == 0){ %>
            <div class="card  mb-3" style="max-width: 18rem; border-color: #cdcbcb; border-radius: 15px; position:relative; ">
                <div class="card-header"><%=result.get(i).getcaseTitle()%></div>
                <div class="card-body " style="color: #cdcbcb">
                    <h5 class="card-title" style ="font-weight:bold; font-size: 20px"><%=simpleDateFormatforDueDate.format(result.get(i).getcaseDueDate())%></h5>
                    <p class="card-text"><%=result.get(i).getcaseDescription()%></p>
                    <div style="position:absolute; bottom: -5px; width: 100%;">
                        <div style="margin-left: 45%; display: grid; grid-template-columns: 30% 30% 36%;grid-column-gap: 0%;">
                            <div style="width: 18px;margin-right: 0;">
                                <form method="POST" action="/deletecase" style="width: 18px; margin-right: 0;">
                                    <input type="hidden" name="actionType" value="choice_of_case">
                                    <button class="DeleteUserFromGroup" type="submit" name="caseNumber" value="<%=result.get(i).getcaseNumber()%>">
                                        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M6 19C6 20.1 6.9 21 8 21H16C17.1 21 18 20.1 18 19V7H6V19ZM19 4H15.5L14.5 3H9.5L8.5 4H5V6H19V4Z" fill="#C4C4C4"/>
                                        </svg>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <%}%>
        <%}%>
    </div>
    </div>
</c:if>
  <c:if test="${thisuser.getUserActive() == 0}">
    <p style="color:#de4966">You've been banned</p>
  </c:if>
  <jsp:include page="footer.jsp"/>
</main>
</body>
</html>
