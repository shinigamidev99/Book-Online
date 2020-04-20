<%-- 
    Document   : handler_error
    Created on : Dec 25, 2019, 1:04:05 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap-3.1.1-dist/css/bootstrap.css">
        <title>Error 404</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container-fluid">
                <h1>Sorry, this content is currently unavailable</h1>
                <p>The link you have followed may have expired or the page may only be visible to audiences that do not include you.</p>
            </div>      
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
