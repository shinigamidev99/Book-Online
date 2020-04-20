<%-- 
    Document   : home
    Created on : Dec 3, 2019, 4:19:38 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Header Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="container-fluid alert-black">
                <div class="row">
                    <div class="col-md-3 col-lg-3">
                        <a><i>Welcome Thành Đạt Book Store</i></a>
                    </div>
                    <div class="col-md-9 col-lg-9">
                        <div class="container-link">
                            <c:if test="${not empty sessionScope.login}">
                                Hello,&nbsp;
                                <c:if test="${sessionScope.login.role eq 'user'}">
                                    <a data-toggle="modal" href="#myModal" class="text-link" title="Your Profile">
                                        <img src="image/account.png" alt="login" class="img-thumbnail-shop">${sessionScope.login.customerByUsername.fullName}
                                    </a>
                                </c:if>
                                <c:if test="${sessionScope.login.role eq 'admin'}">
                                    <a href="admin" class="text-link" title="Management Book">
                                        <img src="image/account.png" alt="login" class="img-thumbnail-shop">${sessionScope.login.username}
                                    </a>
                                </c:if>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="logout" class="text-link">
                                    <img src="image/logout.png" alt="login" class="img-thumbnail-shop">Logout
                                </a>
                            </c:if>
                            <c:if test="${empty sessionScope.login}">
                                <a href="login" class="text-link"><img src="image/login.png" alt="login" class="img-thumbnail-shop">
                                    Login
                                </a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="register" class="text-link">
                                    <img src="image/register.png" alt="login" class="img-thumbnail-shop">Register
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid alert-shop">
                <div class="row">
                    <div class="col-md-4 col-lg-4 col-xs-4">
                        <div class="row">
                            <div class="col-md-2 col-lg-2 col-xs-2  row-logo">
                                <img src="image/bookStore.png" class="img-size">
                            </div>
                            <div class="col-md-4 col-lg-4">
                                <label class="text-logo">Thành Đạt Book Store</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4 col-xs-4 support">
                        <div class="row">
                            <div class="col-md-4 col-lg-4 col-xs-4">
                                <label><img src="image/ship.png" alt="ship"> Free ship</label>
                            </div>
                            <div class="col-md-4 col-lg-4 col-xs-4">
                                <label><img src="image/return.png" alt="return"> Free returns</label>
                            </div>
                            <div class="col-md-4 col-lg-4 col-xs-4">
                                <label><img src="image/support.png" alt="support"> Support 24/7</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4 col-xs-4 cart">
                        <label class="label-cart">
                            <a href="cart" class="text-cart"><img src="image/cart.png" alt="cart"> Product cart 
                                <span class="number-product">${fn:length(sessionScope.cart.items)}</span>
                            </a> 
                        </label>
                    </div>
                </div>
            </div>
            <div class="container-fluid" style="background-color: #3366ff; color: #ffffff">
                <div class="row">
                    <div class="col-md-4 col-lg-4 col-xs-12">
                        <div class="row menu">
                            <div class="col-md-2 col-lg-2 col-xs-2 my-item">
                                <div class="dropdown-my">
                                    <a href="home"><button class="dropbtn-my">Home</button></a>
                                </div>
                            </div>
                            <div class="col-md-3 col-lg-3 col-xs-3 my-item">
                                <div class="dropdown-my">
                                    <button class="dropbtn-my"> Category <img src="image/drop.png" alt="drop"></button>
                                    <div class="dropdown-content">
                                        <c:if test="${not empty categories}">
                                            <c:forEach var="cate" items="${categories}">
                                                <a href="home?page=1&categoryId=${cate.categoryId}&isSearch=${cate.categoryName}">${cate.categoryName}</a></option>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-lg-3 col-xs-3 my-item">
                                <div class="dropdown-my">
                                    <button class="dropbtn-my"> Publishers <img src="image/drop.png" alt="drop"></button>
                                    <div class="dropdown-content">
                                        <c:if test="${not empty categories}">
                                            <c:forEach var="p" items="${publishers}">
                                                <a href="home?page=1&publicationId=${p.publicationId}&isSearch=${p.publicationName}">${p.publicationName}</a></option>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2 col-lg-2 col-xs-2 my-item">
                                <div class="dropdown-my">
                                    <button class="dropbtn-my"> Author <img src="image/drop.png" alt="drop"></button>
                                    <div class="dropdown-content">
                                        <c:if test="${not empty authors}">
                                            <c:forEach var="a" items="${authors}">
                                                <a href="home?page=1&authorId=${a.authorId}&isSearch=${a.authorName}">${a.authorName}</a></option>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2 col-lg-2 col-xs-2 my-item">
                                <div class="dropdown-my">
                                    <a href="home?page=1&sale=1"><button class="dropbtn-my">Sale</button></a>
                                </div>
                            </div>
                        </div>
                    </div>                        
                    <div class="col-md-4 col-lg-4 col-xs-12">
                        <form action="home">
                            <input type="hidden" class="form-control" name="page" value="1"> 
                            <div class="row search">
                                <div class="col-md-3 col-lg-3 col-xs-3">
                                    <select name="categoryId" class="form-control">
                                        <option value="-1">All</option>
                                        <c:if test="${not empty categories}">
                                            <c:forEach var="cate" items="${categories}">
                                                <option value="${cate.categoryId}" ${param.categoryId == cate.categoryId?'selected':''}>${cate.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="col-md-6 col-lg-6 col-xs-6 my-item">
                                    <input type="text" class="form-control" name="title" placeholder="search..." value="${param.title}">
                                </div>
                                <div class="col-md-1 col-lg-1 col-xs-1 my-item">
                                    <input type="submit" value="Search" name="btnSearch" class="btn btn-success"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3 col-lg-3 col-xs-12 blog-contact">
                        <div class="row menu">
                            <div class="col-md-6 col-lg-6 col-xs-5 my-item">
                                <div class="dropdown-my">
                                    <a href="#"><button class="dropbtn-my">Blog</button></a>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-6 col-xs-5 my-item">
                                <div class="dropdown-my">
                                    <a href="#"><button class="dropbtn-my">Contact</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>    
            <c:if test="${not empty sessionScope.login}">
                <form action="update-account" method="POST">
                    <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">
                                        PROFILE ${sessionScope.login.customerByUsername.fullName}
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            First name
                                            <input type="text" class="form-control" name="firstName" value="${sessionScope.login.customerByUsername.firstName}"/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Last name
                                            <input type="text" class="form-control" name="lastName" value="${sessionScope.login.customerByUsername.lastName}"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Username
                                            <input type="text" class="form-control" name="username" value="${sessionScope.login.username}" disabled/>
                                            <input type="hidden" class="form-control" name="username" value="${sessionScope.login.username}"/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Gender
                                            <select name="gender" class="form-control">
                                                <option value="1"  ${sessionScope.login.customerByUsername.gender?'selected':''}>Male</option>
                                                <option value="0" ${!sessionScope.login.customerByUsername.gender?'selected':''}>Female</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Phone
                                            <input type="number" class="form-control" name="phone" value="${sessionScope.login.customerByUsername.phone}"/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Email
                                            <input type="email" class="form-control" name="email" value="${sessionScope.login.customerByUsername.email}"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Street
                                            <input type="text" class="form-control" name="street" value="${sessionScope.login.customerByUsername.street}"/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            City
                                            <input type="text" class="form-control" name="city" value="${sessionScope.login.customerByUsername.city}"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Zip code
                                            <input type="number" class="form-control" name="zipCode" value="${sessionScope.login.customerByUsername.zip_code}"/>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-md-6">
                                            Change your password<br>
                                            <a href="change-password"><input type="button" class="btn btn-warning form-control" value="Change" name="changePass"/></a>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary" name="submit">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
    </body>
</html>
