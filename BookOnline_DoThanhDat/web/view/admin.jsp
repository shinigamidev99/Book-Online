<%-- 
    Document   : admin
    Created on : Dec 19, 2019, 4:14:15 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/admin.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.pack.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.pack.js"></script>
    </head>
    <body>
        <jsp:useBean id="p" class="bean.PagingBean"/>
        <jsp:setProperty name="p" property="*"/>
        <form class="navbar-form navbar-left display" action=""> 
            <div class="container-fluid">
                <table class="table table-dark table-hover" style="text-align: center;">
                    <thead>
                        <tr>
                            <td colspan="15">
                                <h2 style="text-align: center;font-weight: bold"> Book Management</h2>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h6 style="text-align: center;font-weight: bold; color: #189eff">Hi, ${sessionScope.login.username}</h6>
                            </td>
                            <td colspan="1">
                                <a href="home"><button type="button" class="btn btn-primary"><i class="fa fa-angle-left"></i> Go home </button></a>
                            </td>
                            <td colspan="1">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalPublisher">Publisher</button>
                            </td>
                            <td colspan="1">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalAuthor">Author</button>
                            </td>
                            <td colspan="1">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalAdd">Add</button>
                            </td>
                            <td colspan="3">
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModalOrder">View Order</button>
                            </td>
                            <td colspan="4">
                                <input type="text" class="form-control"  placeholder="Search book" name="title" value="${param.search}"> 
                            </td>
                            <td colspan="1">
                                <button type="submit" class="btn btn-success">Search</button>
                            </td>
                            <td colspan="1">
                                <a href="logout"><button type="button" class="btn btn-danger">Logout</button></a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="15">
                                <div class="container-fluid" style="text-align: center;">
                                    <ul class="pagination">
                                        <c:forEach var="i" begin="1" end="${p.pages}" step="1">
                                            <c:url value="admin" var="next">
                                                <c:forEach var="para" items="${parameters}">
                                                    <c:param name="${para.name}" value="${para.name eq 'page' ? i : para.value}"/> 
                                                </c:forEach>
                                                <c:if test="${empty param.page}">
                                                    <c:param name="page" value="${i}"/>
                                                </c:if>
                                            </c:url>

                                            <c:choose>
                                                <c:when test="${param.page eq i || (empty param.page && i == 1)}">

                                                    <li><a style="background-color: #3278b3; color: #FFF">${i}</a></li>

                                                </c:when>
                                                <c:otherwise>

                                                    <li><a href="${next}">${i}</a></li>

                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                        <c:if test="${param.page lt p.pages || (empty param.page && p.pages > 1)}">
                                            <c:url value="admin" var="next">
                                                <c:forEach var="para" items="${parameters}">
                                                    <c:param name="${para.name}" value="${para.name eq 'page' ? (para.value + 1) : para.value}"/> 
                                                </c:forEach>
                                                <c:if test="${empty param.page}">
                                                    <c:param name="page" value="2"/> 
                                                </c:if>
                                            </c:url>

                                            <li><a href="${next}">Next </a></li>

                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </thead>
                    <tr>
                        <th>BOOK ID</th>
                        <th>CATEGORY</th>
                        <th>TITLE</th>
                        <th>PUBLISHER</th>
                        <th>PUBLICATION DATE</th>
                        <th>PRICE</th>
                        <th>QUANTITY</th>
                        <th>NOTES</th>
                        <th>IMAGE</th>
                        <th>POD</th>
                        <th>AUTHOR</th>
                        <th>DISCOUNT</th>
                        <th>START</th>
                        <th>END</th>
                        <th>MANAGEMENT</th>
                    </tr>
                    <tbody>
                        <c:forEach var="b" items="${p.books}">
                            <tr>
                                <td >${b.bookId}</td>
                                <td><c:forEach var="cate" items="${b.categoryNameById}">${cate.categoryName} </c:forEach></td>
                                <td>${b.title}</td>
                                <td>
                                    <p style="white-space: nowrap; width: 100px; overflow: hidden; text-overflow: ellipsis;">
                                        <c:forEach var="pub" items="${b.publicationNameById}">
                                            ${pub.publicationName}
                                        </c:forEach>
                                    </p>
                                </td>
                                <td>${b.publicationDate}</td>
                                <td>${b.price}</td>
                                <td>${b.quantity}</td>
                                <td><p style="white-space: nowrap; width: 200px; overflow: hidden; text-overflow: ellipsis;">${b.notes}</p></td>
                                <td>${b.image}</td>
                                <td>${b.pod}</td>
                                <td><c:forEach var="au" items="${b.authorNameById}">${au.authorName}</c:forEach></td>
                                <td><c:forEach var="dis" items="${b.allBookDiscount}">
                                        <c:if test="${b.bookId eq dis.bookId}">
                                            ${dis.discountFormat}%
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td><c:forEach var="dis" items="${b.allBookDiscount}">
                                        <c:if test="${b.bookId eq dis.bookId}">
                                            ${dis.startDate}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td><c:forEach var="dis" items="${b.allBookDiscount}">
                                        <c:if test="${b.bookId eq dis.bookId}">
                                            ${dis.endDate}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-info btn-sm" title="Edit" data-toggle="modal" data-target="#myModal${b.bookId}"><i class="fa fa-edit"></i></button>
                                    <a href="admin?action=detele&bookId=${b.bookId}&page=${empty param.page ? 1 : param.page}"  onclick="return confirm('Do your want to delete this book?')"><button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="15">
                                <div class="container-fluid" style="text-align: center;">
                                    <ul class="pagination">
                                        <c:forEach var="i" begin="1" end="${p.pages}" step="1">
                                            <c:url value="admin" var="next">
                                                <c:forEach var="para" items="${parameters}">
                                                    <c:param name="${para.name}" value="${para.name eq 'page' ? i : para.value}"/> 
                                                </c:forEach>
                                                <c:if test="${empty param.page}">
                                                    <c:param name="page" value="${i}"/>
                                                </c:if>
                                            </c:url>

                                            <c:choose>
                                                <c:when test="${param.page eq i || (empty param.page && i == 1)}">

                                                    <li><a style="background-color: #3278b3; color: #FFF">${i}</a></li>

                                                </c:when>
                                                <c:otherwise>

                                                    <li><a href="${next}">${i}</a></li>

                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                        <c:if test="${param.page lt p.pages || (empty param.page && p.pages > 1)}">
                                            <c:url value="admin" var="next">
                                                <c:forEach var="para" items="${parameters}">
                                                    <c:param name="${para.name}" value="${para.name eq 'page' ? (para.value + 1) : para.value}"/> 
                                                </c:forEach>
                                                <c:if test="${empty param.page}">
                                                    <c:param name="page" value="2"/> 
                                                </c:if>
                                            </c:url>

                                            <li><a href="${next}">Next </a></li>

                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <c:forEach var="b" items="${p.books}">                    
            <form action="update">
                <div id="myModal${b.bookId}" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <input type="hidden" name="bookId" value="${b.bookId}"/>
                                <h4 class="modal-title">
                                    ${b.title}
                                </h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Title <input type="text" class="form-control" name="title" value="${b.title}"/></div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Categories
                                            <select class="form-control" name="categoryId">
                                                <c:forEach var="cate" items="${categories}">
                                                    <option value="${cate.categoryId}" ${cate.categoryId == b.categoryId?'selected':''}>${cate.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Publishers
                                            <select class="form-control" name="publicationId">
                                                <c:forEach var="pub" items="${publishers}">
                                                    <option value="${pub.publicationId}" ${pub.publicationId == b.publicationId?'selected':''}>${pub.publicationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Publication date <input type="date" class="form-control" name="publicationDate" value="${b.publicationDate}"/></div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Price 
                                            <div class="input-group">
                                                <input type="text" class="form-control" name="price" value="${b.price}"/>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">$</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Quantity <input type="text" class="form-control" name="quantity" value="${b.quantity}"/></div>
                                    </div>
                                </div>

                                <div>Notes
                                    <textarea name="notes" rows="4" cols="20" class="form-control">${b.notes}</textarea>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Image
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="customFile" name="image">
                                                <label class="custom-file-label" for="customFile">${b.image}</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Author
                                            <select class="form-control" name="authorId">
                                                <c:forEach var="au" items="${authors}">
                                                    <option value="${au.authorId}" ${au.authorId == b.authorId?'selected':''}>${au.authorName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>Discount
                                            <div class="input-group">
                                                <input type="text" class="form-control" value="${b.discount.discountFormat}" name="discount">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">%</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>Start date
                                            <input type="date" class="form-control" name="startDate" value="${b.discount.startDate}"/>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>End date
                                            <input type="date" class="form-control" name="endDate" value="${b.discount.endDate}"/>
                                        </div>
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
        </c:forEach>

        <form action="add">
            <div id="myModalAdd" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">
                                ADD A NEW BOOK
                            </h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-4 col-lg-4 col-xs-4"> 
                                    <div class="radio">
                                        <label><input type="radio" name="bookIdRadio" id="availId" value="0" checked>Available Id</label>
                                    </div>
                                </div>
                                <div class="col-md-8 col-lg-8 col-xs-8">
                                    <div class="radio">
                                        <select class="form-control" name="bookId" id="bookIdAvail">
                                            <c:forEach var="entry" items="${bookIdAlivable}">
                                                <option value="${entry.key}_00${entry.value + 1}">${entry.key}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>&nbsp;<div>
                                <div class="row">
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div class="radio">
                                            <label><input type="radio" name="bookIdRadio" id="newId" value="1">New Id</label>
                                        </div>
                                    </div>
                                    <div class="col-md-8 col-lg-8 col-xs-8">
                                        <div class="radio">
                                            <input type="text" class="form-control" name="bookId" id="bookIdNew" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Title <input type="text" class="form-control" name="title" value=""/></div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Categories
                                            <select class="form-control" name="categoryId">
                                                <c:forEach var="cate" items="${categories}">
                                                    <option value="${cate.categoryId}">${cate.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Publishers
                                            <select class="form-control" name="publicationId">
                                                <c:forEach var="pub" items="${publishers}">
                                                    <option value="${pub.publicationId}">${pub.publicationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Publication date <input type="date" class="form-control" name="publicationDate" value="${b.publicationDate}"/></div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Price 
                                            <div class="input-group">
                                                <input type="text" class="form-control" name="price" value=""/>
                                                <div class="input-group-append">
                                                    <span class="input-group-text">$</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Quantity <input type="text" class="form-control" name="quantity" value=""/></div>
                                    </div>
                                </div>

                                <div>Notes
                                    <textarea name="notes" rows="4" cols="20" class="form-control"></textarea>
                                </div>

                                <div class="row">
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Image
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="customFile" name="image">
                                                <label class="custom-file-label" for="customFile"></label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xs-6">
                                        <div>Author
                                            <select class="form-control" name="authorId">
                                                <c:forEach var="au" items="${authors}">
                                                    <option value="${au.authorId}">${au.authorName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>Discount
                                            <div class="input-group">
                                                <input type="text" class="form-control" value="" name="discount">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">%</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>Start date
                                            <input type="date" class="form-control" name="startDate" value=""/>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-lg-4 col-xs-4">
                                        <div>End date
                                            <input type="date" class="form-control" name="endDate" value=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" name="submit">Add</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div id="myModalPublisher" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            PUBLISHER
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-xs-6" style="text-align: center;">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalAddPublisher">Add</button>
                            </div>
                            <div class="col-md-6 col-lg-6 col-xs-6" style="text-align: center;">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalManagementPublisher">Management</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form action="add-publisher">
            <div id="myModalAddPublisher" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">
                                ADD A NEW PUBLISHER
                            </h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <div>Publisher Id
                                <input type="text" class="form-control" name="publicationId" value=""/>
                            </div>
                            <div>Publisher Name
                                <input type="text" class="form-control" name="publicationName" value=""/>
                            </div>
                            <div>Publisher Address
                                <input type="text" class="form-control" name="publicationAddress" value=""/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="submit">Add</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div id="myModalManagementPublisher" class="modal fade" tabindex="-1" role="dialog" style="">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            MANAGEMENT PUBLISHER
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-dark table-hover" style="text-align: center; width: 100%;">
                            <tbody>
                                <tr>
                                    <th>ID</th>
                                    <th>NAME</th>
                                    <th>ADDRESS</th>
                                    <th>MANAGE</th>
                                </tr>
                                <c:forEach var="pub" items="${publishers}">
                                    <tr>
                                        <td>${pub.publicationId}</td>
                                        <td>${pub.publicationName}</td>
                                        <td>${pub.publicationAddress}</td>
                                        <td>
                                            <button type="button" class="btn btn-info btn-sm" title="Edit" data-toggle="modal" data-target="#myModal${pub.publicationId}"><i class="fa fa-edit"></i></button>
                                            <a href="management-publisher?action=delete&publicationId=${pub.publicationId}" title="delete" onclick="return confirm('Do your want to delete this publisher?')"><button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <c:forEach var="pub" items="${publishers}">
            <form action="management-publisher">
                <div id="myModal${pub.publicationId}" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">
                                    ${pub.publicationName}
                                    <input type="hidden" class="form-control" name="action" value="update"/>
                                </h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <div>Publisher Id
                                    <input type="text" class="form-control" name="publicationId" value="${pub.publicationId}" disabled/>
                                    <input type="hidden" class="form-control" name="publicationId" value="${pub.publicationId}"/>
                                </div>
                                <div>Publisher Name
                                    <input type="text" class="form-control" name="publicationName" value="${pub.publicationName}"/>
                                </div>
                                <div>Publisher Address
                                    <input type="text" class="form-control" name="publicationAddress" value="${pub.publicationAddress}"/>
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
        </c:forEach>

        <div id="myModalAuthor" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            AUTHOR
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 col-lg-6 col-xs-6" style="text-align: center;">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalAddAuthor">Add</button>
                            </div>
                            <div class="col-md-6 col-lg-6 col-xs-6" style="text-align: center;">
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModalManagementAuthor">Management</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form action="add-author">
            <div id="myModalAddAuthor" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">
                                ADD A NEW AUTHOR
                            </h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <div>Author Id
                                <input type="text" class="form-control" name="authorId" value=""/>
                            </div>
                            <div>Author Name
                                <input type="text" class="form-control" name="authorName" value=""/>
                            </div>
                            <div>Author Address
                                <input type="text" class="form-control" name="authorAddress" value=""/>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="submit">Add</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div id="myModalManagementAuthor" class="modal fade" tabindex="-1" role="dialog" style="">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            MANAGEMENT AUTHOR
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-dark table-hover" style="text-align: center; width: 100%;">
                            <tbody>
                                <tr>
                                    <th>ID</th>
                                    <th>NAME</th>
                                    <th>ADDRESS</th>
                                    <th>MANAGE</th>
                                </tr>
                                <c:forEach var="au" items="${authors}">
                                    <tr>
                                        <td>${au.authorId}</td>
                                        <td>${au.authorName}</td>
                                        <td>${au.authorAddress}</td>
                                        <td>
                                            <button type="button" class="btn btn-info btn-sm" title="Edit" data-toggle="modal" data-target="#myModal${au.authorId}"><i class="fa fa-edit"></i></button>
                                            <a href="management-author?action=delete&authorId=${au.authorId}" title="delete" onclick="return confirm('Do your want to delete this author?')"><button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <c:forEach var="au" items="${authors}">
            <form action="management-author">
                <div id="myModal${au.authorId}" class="modal fade" tabindex="-1" role="dialog" style="margin-top: 5%;">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">
                                    ${au.authorName}
                                    <input type="hidden" class="form-control" name="action" value="update"/>
                                </h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <div>Author Id
                                    <input type="text" class="form-control" name="authorId" value="${au.authorId}" disabled/>
                                    <input type="hidden" class="form-control" name="authorId" value="${au.authorId}"/>
                                </div>
                                <div>Author Name
                                    <input type="text" class="form-control" name="authorName" value="${au.authorName}"/>
                                </div>
                                <div>Author Address
                                    <input type="text" class="form-control" name="authorAddress" value="${au.authorAddress}"/>
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
        </c:forEach>

        <div id="myModalOrder" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            Order
                        </h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <c:forEach var="bill" items="${bills}">
                            <table class="table table-dark" style="width: 100%;" border="1">
                                <tbody>
                                    <tr>
                                        <td colspan="4">BILL ID: ${bill.billId}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">CUSTOMER: ${bill.customerById.lastName} ${bill.customerById.firstName} (${bill.customerId})</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">ADDRESS: ${bill.address}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">DATE CREATE: ${bill.dateCreated}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">TOTAL: ${bill.total}$</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div class="row">
                                                <div class="col-lg-1 col-md-1 col-xs-1">STATUS:</div>
                                                <c:if test="${bill.status == 0}">
                                                    <div class="col-lg-6 col-md-6 col-xs-6" style="color: #3366ff">
                                                        Processing
                                                        <a href="order?action=status&billId=${bill.billId}&status=1"><button type="button" class="btn btn-success btn-sm" title="Accept"><i class="fa fa-check-circle"></i></button></a>
                                                        <a href="order?action=status&billId=${bill.billId}&status=-1"><button type="button" class="btn btn-danger btn-sm" title="Reject"><i class="fa fa-ban"></i></button></a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${bill.status == 1}">
                                                    <div class="col-lg-6 col-md-6 col-xs-6" style="color: #33ff33">Access</div>
                                                </c:if>
                                                <c:if test="${bill.status == -1}">
                                                    <div class="col-lg-6 col-md-6 col-xs-6" style="color: #ff3333">Reject</div>
                                                </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <table class="table table-dark table-hover" style="text-align: center; width: 100%;" border="1">
                                                <tbody>
                                                    <tr>
                                                        <th>ITEM ID</th>
                                                        <th>BOOK</th>
                                                        <th>QUANTITY</th>
                                                        <th>PRICE</th>
                                                        <th>DELETE</th>
                                                    </tr>
                                                    <c:forEach var="billsDentails" items="${billDentails}">
                                                        <c:if test="${bill.billId == billsDentails.billId}">
                                                            <tr>
                                                                <td>${billsDentails.itemId}</td>
                                                                <td>${billsDentails.bookById.title}</td>
                                                                <td>${billsDentails.quantity}</td>
                                                                <td>${billsDentails.price}</td>
                                                                <td>
                                                                    <c:if test="${bill.status == 0}">

                                                                        <a href="order?action=delete&itemId=${billsDentails.itemId}&quantity=${billsDentails.quantity}&bookId=${billsDentails.bookId}&billId=${billsDentails.billId}" title="delete" onclick="return confirm('Do your want to delete this book?')"><button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button></a>

                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:forEach>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <script language="javascript" src="bootstrap-3.1.1-dist/js/admin.js"></script>
        <script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.10.1.min.js"></script>
    </body>
</html>
