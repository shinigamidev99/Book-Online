<%-- 
    Document   : Login
    Created on : Dec 15, 2019, 8:20:34 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/login.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css">
        <title>Login page</title>
    </head>
    <body>
        <div class="container" style="width: 30%">
            <div class="jumbotron" style="line-height: 50px;">
                <form action="login" method="post">
                    <h1 style="text-align: center" class="firstshadow"><label>Login</label></h1>
                    <div class="row">
                        <div class="col-md-4 col-lg-4 col-xs-4 seconshadow">
                            Username
                        </div>
                        <div class="col-md-8 col-lg-8 col-xs-8 padding-zero">
                            <input type="text" name="username" placeholder="Enter username" class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 col-lg-4 col-xs-4 seconshadow">
                            Password
                        </div>
                        <div class="col-md-8 col-lg-8 col-xs-8">
                            <div class="row">
                                <div class="input-group">
                                    <input type="password" id="pwd" placeholder="Enter your password" name="password" class="form-control">
                                    <div class="input-group-append">
                                        <span class="input-group-text"><i class="fa fa-eye" id="eye"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-xs-12">
                            <input type="checkbox" name="remember" value="ON" /><b > Remember me</b>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-xs-12" style="padding-left: 34%;">
                            <input type="submit" value="Submit" name="submit" class="btn btn-primary btn-lg" style="width: 50%;"/>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-12 col-xs-12">
                        <a href="forgot-your-password" style="text-decoration: none;">Lost your password?</a>
                    </div>
                    <div class="col-md-12 col-lg-12 col-xs-12">
                        <a href="register" style="text-decoration: none;">Register new account</a>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-lg-12 col-xs-12">
                            <c:if test="${not empty sessionScope.error}">
                                <div class="alert alert-danger">
                                    <strong>Warning!</strong> ${sessionScope.error}
                                </div>
                            </c:if>
                            <c:if test="${not empty sessionScope.register}">
                                <div class="alert alert-success">
                                    <strong>Successful!</strong> ${sessionScope.register}
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-12 col-xs-12" style="text-align: right;">
                        <a href="home">Go Home <i class="fa fa-angle-right" id="eye"></i></a>
                    </div>
                </form>
            </div>
        </div>

        <script>
            var pwd = document.getElementById('pwd');
            var eye = document.getElementById('eye');

            eye.addEventListener('click', togglePass);

            function togglePass() {
                eye.classList.toggle('active');

                (pwd.type === 'password') ? pwd.type = 'text' :
                        pwd.type = 'password';
            }
        </script>
    </body>
</html>
