<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 01.04.2022
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body >
<main >
    <section class="vh-100" style="background-image: url(https://images.pexels.com/photos/1454794/pexels-photo-1454794.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow-2-strong" style="border-radius: 2rem; background: #d5d4d4;">
                        <div class="card-body p-5 text-center">
                            <form method="POST" action="/authorization/">

                                <h3 class="mb-5" style="color: #FF9A8D;">My planner</h3>

                                <div class="form-outline mb-4">
                                    <input name="login" id="typeEmailX-2" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                    <label class="form-label" for="typeEmailX-2" style="color:#4A538B ">Login</label>
                                </div>

                                <div class="form-outline mb-4">
                                    <input name="password" type="password" id="typePasswordX-2" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                    <label class="form-label" for="typePasswordX-2" style="color:#4A538B ">Password</label>
                                </div>

                                <button style="background: #AED6DC;border-radius: 15px;width: 50%;
                                               color: #4A538B;font-size: 18px;border: none; outline: none;
                                               font-weight:bold;" type="submit">
                                    Sign in
                                </button>
                                <div >
                                    <a  href="/registration">registration</a>
                                </div>
                                <p style="color:#de4966"><c:out value="${message}"></c:out></p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
