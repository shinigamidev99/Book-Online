<%-- 
    Document   : body
    Created on : Dec 5, 2019, 8:10:27 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty param.isSearch || not empty param.btnSearch}">
            <jsp:include page="body_second.jsp"></jsp:include>
        </c:if>
        <c:if test="${empty param.isSearch && empty param.btnSearch}">
            <c:if test="${not empty param.sale}">
                <jsp:include page="body_sale.jsp"></jsp:include>
            </c:if>
            <c:if test="${empty param.sale}">
                <jsp:include page="body_first.jsp"></jsp:include>
            </c:if>
        </c:if>
    </body>
</html>
