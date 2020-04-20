<%-- 
    Document   : change-password
    Created on : Dec 24, 2019, 10:08:35 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/change_pass.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css">
        <title>Change password</title>
    </head>
    <body>
        <div class="container" style="width: 30%">
            <div class="jumbotron" style="line-height: 50px;">
                <form action="change-password" method="post">
                    <h1>Change password</h1>
                    Current Password
                    <input type="password" class="form-control" name="currentPassword" value=""/>
                    New Password
                    <input type="password" class="form-control" name="newPassword" id="newPassword" value=""/>
                    Confirm new password
                    <input type="password" class="form-control" name="rePassword" id="rePassword" value=""/>
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6" style="text-align: left;">
                            <a href="home"><i class="fa fa-angle-left"></i> Go Home</a>
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6" style="text-align: right;">
                            <button type="submit" class="btn btn-primary" name="submit">Update</button>
                        </div>
                    </div>
                </form>
                <c:if test="${not empty sessionScope.error}">
                    <c:if test="${sessionScope.error eq 'no'}">
                        <div class="alert alert-success">
                            <strong>Success!</strong> Change password done!
                        </div>
                    </c:if>
                    <c:if test="${!(sessionScope.error eq 'no')}">
                        <div class="alert alert-warning">
                            <strong>Warning!</strong> ${sessionScope.error}
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
