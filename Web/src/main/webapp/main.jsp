<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 12.04.2022
  Time: 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class=" background-image: url(${pageContext.request.contextPath}/style/image/pexels-photo-1454794.jpeg);
  position: fixed;">
<main style="position: absolute;">
    <div style="margin:100px 500px;">
        <div style="width: 350px;"><h1 style="color:#FF9A8D; font-size: 64px;">My planner</h1></div>
        <br>
        <p style="color: #4A538B; font-size: 34px; margin: 0 -50px;" >A planner for your daily tasks</p>
        <br><br>
        <div style="background: #AED6DC;border-radius: 15px; width: 50%; color: #4A538B;
        font-size: 18px;border: none; outline: none; margin: 0 80px; ">
            <a href="/authorization" style="text-decoration: none; color: #4A538B;font-size: 25px; margin: 0 60px;">start</a>
        </div>
         </div>
</main>
</body>
</html>
