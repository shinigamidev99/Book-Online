<%-- 
    Document   : body_second
    Created on : Dec 10, 2019, 8:07:26 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.pack.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="p" class="bean.PagingBean"/>
        <jsp:setProperty name="p" property="*"/>
        <div class="container-fluid">
            <div>
                <h3>
                    <span>
                        <i class="glyphicon glyphicon-book"></i> ${param.isSearch == null ? "The result your search ": param.isSearch}
                    </span>
                    <hr>
                </h3>
            </div>
        </div>  
        <div class="container-fluid" style="text-align: center;">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${p.pages}" step="1">
                    <c:url value="home" var="next">
                        <c:forEach var="para" items="${parameters}">
                            <c:param name="${para.name}" value="${para.name eq 'page' ? i : para.value}"/> 
                        </c:forEach>
                    </c:url>

                    <c:choose>
                        <c:when test="${param.page eq i}">

                            <li><a style="background-color: #3278b3; color: #FFF">${i}</a></li>

                        </c:when>
                        <c:otherwise>

                            <li><a href="${next}">${i}</a></li>

                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                <c:if test="${param.page lt p.pages}">
                    <c:url value="home" var="next">
                        <c:forEach var="para" items="${parameters}">
                            <c:param name="${para.name}" value="${para.name eq 'page' ? (para.value + 1) : para.value}"/> 
                        </c:forEach>
                    </c:url>

                    <li><a href="${next}">Next </a></li>

                </c:if>
            </ul>
        </div>

        <div class="container-fluid">
            <div class="col-md-12 col-lg-12 col-xs-12">
                <div class="row">
                    <c:if test="${not empty p.books}">
                        <c:forEach var="b" items="${p.books}">   
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
                                                                <c:forEach var="pu" items="${b.publicationNameById}">
                                                                    ${pu.publicationName}&nbsp;
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
                                    <c:forEach var="pu" items="${b.publicationNameById}">
                                        ${pu.publicationName}&nbsp;
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

        <div class="container-fluid" style="text-align: center;">
            <ul class="pagination">
                <c:forEach var="i" begin="1" end="${p.pages}" step="1">
                    <c:url value="home" var="next">
                        <c:forEach var="para" items="${parameters}">
                            <c:param name="${para.name}" value="${para.name eq 'page' ? i : para.value}"/> 
                        </c:forEach>
                    </c:url>

                    <c:choose>
                        <c:when test="${param.page eq i}">

                            <li><a style="background-color: #3278b3; color: #FFF">${i}</a></li>

                        </c:when>
                        <c:otherwise>

                            <li><a href="${next}">${i}</a></li>

                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                <c:if test="${param.page lt p.pages}">
                    <c:url value="home" var="next">
                        <c:forEach var="para" items="${parameters}">
                            <c:param name="${para.name}" value="${para.name eq 'page' ? (para.value + 1) : para.value}"/> 
                        </c:forEach>
                    </c:url>

                    <li><a href="${next}">Next </a></li>

                </c:if>
            </ul>
        </div>
    </body>
</html>
