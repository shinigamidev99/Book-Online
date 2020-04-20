<%-- 
    Document   : body_first
    Created on : Dec 5, 2019, 7:38:25 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.pack.js"></script>
        <title>Body Page</title>
    </head>
    <body>
        <div class="container-fluid">
            <div>
                <h3>
                    <span>
                        <i class="glyphicon glyphicon-book"></i> Hot Book
                    </span>
                    <a href="home?page=1&btnSearch=Search&isSearch=Hot%20Book" target="">
                        View all
                        <i class="glyphicon glyphicon-chevron-right"></i>
                    </a>
                    <hr>
                </h3>
            </div>
        </div>
        <div class="container-fluid">
            <div class="col-md-12 col-lg-12 col-xs-12">
                <div class="row">
                    <c:if test="${not empty books}">
                        <c:forEach var="b" items="${books}">   
                            <div class="col-md-2 col-lg-2 col-xs-2 book-click">
                                <div class="image">
                                    <c:forEach var="dis" items="${discounts}">
                                        <c:if test="${b.bookId == dis.bookId}">
                                            <div class="text-discount">
                                                -${dis.discountFormat}%
                                            </div>
                                        </c:if>
                                    </c:forEach>

                                    <img src="${b.image}" alt="book" class="book">
                                    <div class="txt">
                                        <c:forEach var="dis" items="${discounts}">
                                            <c:if test="${b.bookId == dis.bookId}">
                                                <div class="text-discount">
                                                    -${dis.discountFormat}%
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                        <div class="button_container">
                                            <button type="button" class="quick-view" data-toggle="modal" data-target="#myModal${b.bookId}"><span>Quick View</span></button>
                                        </div>
                                    </div>
                                </div>

                                <div id="myModal${b.bookId}" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 10%;">
                                    <div class="modal-dialog" role="document">
                                        <form action="cart">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title">
                                                        <input type="hidden" name="id" value="${b.bookId}" />
                                                        <input type="hidden" name="name" value="${b.title}" />
                                                        <input type="hidden" name="image" value="${b.image}" />
                                                        <input type="hidden" name="action" value="add" />
                                                        ${b.title}
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-md-6 col-lg-6 col-xs-6">
                                                            <c:forEach var="dis" items="${discounts}">
                                                                <c:if test="${b.bookId == dis.bookId}">
                                                                    <div class="text-discount-dialog">
                                                                        -${dis.discountFormat}%
                                                                    </div>
                                                                </c:if>
                                                            </c:forEach>
                                                            <img src="${b.image}" alt="book" class="modal-img">
                                                        </div>

                                                        <div class="col-md-6 col-lg-6 col-xs-6 information">
                                                            <div class="">
                                                                <b>Category: </b>
                                                                <c:forEach var="cate" items="${b.categoryNameById}">
                                                                    ${cate.categoryName}
                                                                </c:forEach>
                                                                <br>
                                                                <b>Publisher: </b> 
                                                                <c:forEach var="p" items="${b.publicationNameById}">
                                                                    ${p.publicationName}&nbsp;
                                                                </c:forEach>
                                                                <br>
                                                                <b>Author: </b>
                                                                <c:forEach var="a" items="${b.authorNameById}">
                                                                    ${a.authorName}&nbsp;
                                                                </c:forEach>
                                                                <br>
                                                                <b>Price: </b>
                                                                <c:forEach var="dis" items="${discounts}">
                                                                    <c:if test="${b.bookId == dis.bookId}">
                                                                        <del>${b.price}$</del>
                                                                    </c:if>
                                                                </c:forEach>
                                                                ${b.priceReal}$
                                                                <input type="hidden" name="price" value="${b.priceReal}" />
                                                            </div>
                                                            <div class="input-group mb-3">
                                                                <b>Quantity: </b>
                                                                <c:set var="quantity" value = "${b.quantity}" scope="page"/>
                                                                <c:if test="${not empty sessionScope.cart.items}">
                                                                    <c:forEach var="item" items="${sessionScope.cart.items}">
                                                                        <c:if test="${item.id eq b.bookId}">
                                                                            <c:set var="quantity" value = "${b.quantity - item.quantity}" scope="page"/>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <c:if test="${quantity > 0}">
                                                                    <input type="number" class="form-control" name="quantity" min="1" max="<c:out value="${quantity}"/>" value="${param.quantity-buy == 0 ? 1 : param.quantity-buy}">
                                                                </c:if>
                                                                <c:if test="${quantity <= 0}">
                                                                    <p style="color: #ff0000">Out of stock! Sorry continue after!</p>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary" ${b.quantity <= 0 ? 'disabled':''}>Add to cart</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="">
                                    <a href=""><b>Title:</b> ${b.title}</a><br>
                                    <b>Category: </b>
                                    <c:forEach var="cate" items="${b.categoryNameById}">
                                        ${cate.categoryName}&nbsp;
                                    </c:forEach>
                                    <br>
                                    <b>Publisher: </b> 
                                    <c:forEach var="p" items="${b.publicationNameById}">
                                        ${p.publicationName}&nbsp;
                                    </c:forEach>
                                    <br>
                                    <b>Author: </b>
                                    <c:forEach var="a" items="${b.authorNameById}">
                                        ${a.authorName}&nbsp;
                                    </c:forEach>
                                    <br>
                                    <b>Price: </b>
                                    <c:forEach var="dis" items="${discounts}">
                                        <c:if test="${b.bookId == dis.bookId}">
                                            <del>${b.price}$</del>
                                        </c:if>
                                    </c:forEach>
                                    ${b.priceReal}$
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>


        <c:forEach var="cate" items="${categories}">
            <c:if test="${not empty cate.bookByCategoryId}">
                <div class="container-fluid">
                    <div>
                        <h3>
                            <span>
                                <i class="glyphicon glyphicon-book"></i> ${cate.categoryName}
                            </span>
                            <a href="home?page=1&categoryId=${cate.categoryId}&isSearch=${cate.categoryName}" target="">
                                View all
                                <i class="glyphicon glyphicon-chevron-right"></i>
                            </a>
                            <hr>
                        </h3>
                    </div>
                </div>
            </c:if>
            <div class="container-fluid">
                <div class="col-md-12 col-lg-12 col-xs-12">
                    <div class="row">
                        <c:if test="${not empty books}">
                            <c:forEach var="b" items="${cate.bookByCategoryId}">   
                                <div class="col-md-2 col-lg-2 col-xs-2 book-click">
                                    <div class="image">
                                        <c:forEach var="dis" items="${discounts}">
                                            <c:if test="${b.bookId == dis.bookId}">
                                                <div class="text-discount">
                                                    -${dis.discountFormat}%
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                        <img src="${b.image}" alt="book" class="book">
                                        <div class="txt">
                                            <c:forEach var="dis" items="${discounts}">
                                                <c:if test="${b.bookId == dis.bookId}">
                                                    <div class="text-discount">
                                                        -${dis.discountFormat}%
                                                    </div>
                                                </c:if>
                                            </c:forEach>

                                            <div class="button_container">
                                                <button type="button" class="quick-view" data-toggle="modal" data-target="#myModal${b.bookId}"><span>Quick View</span></button>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="myModal${b.bookId}" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 10%;">
                                        <div class="modal-dialog" role="document">
                                            <form action="cart">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        <h4 class="modal-title">
                                                            <input type="hidden" name="id" value="${b.bookId}" />
                                                            <input type="hidden" name="name" value="${b.title}" />
                                                            <input type="hidden" name="image" value="${b.image}" />
                                                            <input type="hidden" name="action" value="add" />
                                                            ${b.title}
                                                        </h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-md-6 col-lg-6 col-xs-6">
                                                                <c:forEach var="dis" items="${discounts}">
                                                                    <c:if test="${b.bookId == dis.bookId}">
                                                                        <div class="text-discount-dialog">
                                                                            -${dis.discountFormat}%
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <img src="${b.image}" alt="book" class="modal-img">
                                                            </div>

                                                            <div class="col-md-6 col-lg-6 col-xs-6 information">
                                                                <div class="">
                                                                    <b>Category: </b>
                                                                    <c:forEach var="cate" items="${b.categoryNameById}">
                                                                        ${cate.categoryName}&nbsp;
                                                                    </c:forEach>
                                                                    <br>
                                                                    <b>Publisher: </b> 
                                                                    <c:forEach var="p" items="${b.publicationNameById}">
                                                                        ${p.publicationName}&nbsp;
                                                                    </c:forEach>
                                                                    <br>
                                                                    <b>Author: </b>
                                                                    <c:forEach var="a" items="${b.authorNameById}">
                                                                        ${a.authorName}&nbsp;
                                                                    </c:forEach>
                                                                    <br>
                                                                    <b>Price: </b>
                                                                    <c:forEach var="dis" items="${discounts}">
                                                                        <c:if test="${b.bookId == dis.bookId}">
                                                                            <del>${b.price}$</del>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    ${b.priceReal}$
                                                                    <input type="hidden" name="price" value="${b.priceReal}" />
                                                                </div>
                                                                <div class="input-group mb-3">
                                                                    <b>Quantity: </b>
                                                                    <input type="number" class="form-control" name="quantity" min="1" max="${b.quantity}" step="1" value="${param.quantity-buy == 0 ? 1 : param.quantity-buy}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                        <button type="submit" class="btn btn-primary">Add to cart</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="">
                                        <a href=""><b>Title:</b> ${b.title}</a><br>
                                        <b>Category: </b>
                                        <c:forEach var="cate" items="${b.categoryNameById}">
                                            ${cate.categoryName}&nbsp;
                                        </c:forEach>
                                        <br>
                                        <b>Publisher: </b> 
                                        <c:forEach var="p" items="${b.publicationNameById}">
                                            ${p.publicationName}&nbsp;
                                        </c:forEach>
                                        <br>
                                        <b>Author: </b>
                                        <c:forEach var="a" items="${b.authorNameById}">
                                            ${a.authorName}&nbsp;
                                        </c:forEach>
                                        <br>
                                        <b>Price: </b>
                                        <c:forEach var="dis" items="${discounts}">
                                            <c:if test="${b.bookId == dis.bookId}">
                                                <del>${b.price}$</del>
                                            </c:if>
                                        </c:forEach>
                                        ${b.priceReal}$
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
