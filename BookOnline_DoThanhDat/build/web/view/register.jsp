<%-- 
    Document   : register
    Created on : Dec 17, 2019, 8:32:19 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <link href="bootstrap-3.1.1-dist/css/register.css" rel="stylesheet">
        <title>Register</title>
    </head>
    <body>
        <div class="container">
            <form action="register" class="form-horizontal" role="form" method="POST">
                <h2>Registration</h2>
                <div class="form-group">
                    <label for="firstName" class="col-sm-3 control-label">First Name</label>
                    <div class="col-sm-9">
                        <input type="text" name="firstName" id="firstName" placeholder="First Name" class="form-control" autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-sm-3 control-label">Last Name</label>
                    <div class="col-sm-9">
                        <input type="text" name="lastName" id="lastName" placeholder="Last Name" class="form-control" autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label for="username" class="col-sm-3 control-label">Username *</label>
                    <div class="col-sm-9">
                        <input type="text" name="username" id="username" placeholder="Username" class="form-control" autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 control-label">Password *</label>
                    <div class="col-sm-9">
                        <input type="password" name="password" id="password" placeholder="Password" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="rePassword" class="col-sm-3 control-label">Confirm Password *</label>
                    <div class="col-sm-9">
                        <input type="password" name="rePassword" id="rePassword" placeholder="Confirm Password" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 control-label">Email *</label>
                    <div class="col-sm-9">
                        <input type="email" id="email" placeholder="Email" class="form-control" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="dob" class="col-sm-3 control-label">Date of Birth</label>
                    <div class="col-sm-9">
                        <input type="date" name="dob" id="dob" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phoneNumber" class="col-sm-3 control-label">Phone *</label>
                    <div class="col-sm-9">
                        <input type="number" name="phoneNumber" id="phoneNumber" placeholder="Phone number" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="street" class="col-sm-3 control-label">Street * </label>
                    <div class="col-sm-9">
                        <input type="text" name="street" id="street" placeholder="Street" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="city" class="col-sm-3 control-label">City * </label>
                    <div class="col-sm-9">
                        <input type="text" name="city" id="city" placeholder="City" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3">Gender</label>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="gender" value="1" checked>Male
                                </label>
                            </div>
                            <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="gender" value="0">Female
                                </label>
                            </div>
                        </div>
                    </div>
                </div> 
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger">
                                <strong>Warning!</strong> ${sessionScope.error}
                            </div>
                        </c:if>
                    </div>
                </div>
                <button type="submit" name="submit" class="btn btn-primary btn-block">Register</button>
                <div class="form-group">
                    <label class="control-label col-sm-3"><a href="login">Go To Login <i class="glyphicon glyphicon-arrow-right"></i></label>
                </div> 
            </form> 
    </body>
</html>
