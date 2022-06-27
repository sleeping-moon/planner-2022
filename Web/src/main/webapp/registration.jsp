<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alyona
  Date: 01.04.2022
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<main >
    <section class="vh-100" style="background-image: url(https://images.pexels.com/photos/1454794/pexels-photo-1454794.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5" style="width: 80%; ">
                    <div class="card shadow-2-strong" style="border-radius: 2rem; background: #d5d4d4;">
                        <div class="card-body p-5 text-center" >
                            <h3 class="mb-5" style="color: #FF9A8D;">My planner</h3>
                            <form method="POST" action="/registration" >
                                <div style="display: grid; grid-template-columns: 47% 47%;
                                            grid-column-gap: 5%;grid-row-gap: 15%;">
                                    <div class="form-outline mb-4">
                                        <input name="name" maxlength="50" id="userName" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                        <label class="form-label" for="userName" style="color:#4A538B ">User Name</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="login" maxlength="50" id="userLogin" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                        <label class="form-label" for="userLogin" style="color:#4A538B ">Login</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="password" maxlength="40" type="password" id="userPassword" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                        <label class="form-label" for="userPassword" style="color:#4A538B ">Password</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="passwordRepeat" maxlength="40" type="password" id="userPasswordRepeat" class="form-control form-control-lg" style="border-radius: 2rem;" />
                                        <label class="form-label" for="userPasswordRepeat" style="color:#4A538B ">Repeat Password</label>
                                    </div>

                                </div>
                                <br><br>
                                <button style="background: #AED6DC;border-radius: 15px;width: 50%;
                                           color: #4A538B;font-size: 18px;border: none; outline: none;
                                           font-weight:bold;" type="submit">
                                    Sign up
                                </button>
                                <div >
                                    <a  href="/authorization">authorization</a>
                                </div>
                            </form>
                            <p style="color:#de4966"><c:out value="${checksuccessinregistration}"></c:out></p>
                        </div >
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
