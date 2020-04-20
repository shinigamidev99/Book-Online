<%-- 
    Document   : forgot_password
    Created on : Dec 17, 2019, 7:42:12 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.css">
        <title>Forgot your password</title>
    </head>
    <body style="background-color: #5a8aeb;">
        <form action="forgot-your-password" style="margin-top: 10%; margin-left: 5%;" method="POST">
            <div class="container">
                <div class="jumbotron">
                    <div class="row">
                        <div class="col-md-1 col-lg-1 col-xs-1  row-logo">
                            <img src="image/bookStore.png" class="img-size">
                        </div>
                        <div class="col-md-10 col-lg-10 col-xs-10">
                            <label class="text-logo-second">Thành Đạt Book Store</label>
                        </div>
                    </div>
                    <div><label>Lost your password? Please enter your username or email address. You will receive a link to create a new password via email.</label></div>
                    <div><label><b>Enter Email</b></label></div>
                    <div><label><input type="email" name="mail" placeholder="Enter your mail" size="40" class="form-control input-lg"></label></div>
                    <div class="row">
                        <div class="col-md-3 col-lg-3 col-xs-3">
                            <button type="submit" name="submit" value="Reset password" class="btn btn-success">Reset password</button>
                        </div>
                        <div class="col-md-6 col-lg-6 col-xs-6">
                            <a href="home">
                                <button type="button" name="sumbit" value="Reset password" class="btn btn-success">
                                    Go Home
                                    <i class="glyphicon glyphicon-arrow-right"></i>
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
                <c:if test="${mailStatus == 'We sended to your mail. Please check your mail!'}">
                    <div class="alert alert-success">
                        <strong>Success!</strong> ${mailStatus}
                    </div>
                </c:if>

                <c:if test="${mailStatus == 'Email is not correct!'}">
                    <div class="alert alert-warning">
                        <strong>Warning!</strong> ${mailStatus}
                    </div>
                </c:if>
            </div>
        </form>
    </body>
</html>
