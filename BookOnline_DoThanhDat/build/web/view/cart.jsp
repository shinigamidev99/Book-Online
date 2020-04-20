<%-- 
    Document   : Cart
    Created on : Dec 12, 2019, 8:40:55 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-3.1.1-dist/css/cart.css">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Cart</title>
    </head>
    <body>
        <h2 class="text-center">Product You Choose</h2>
        <div class="container">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                    <tr>
                        <th style="width:50%">Name product</th>
                        <th style="width:10%">Price</th>
                        <th style="width:8%">Quantity</th>
                        <th style="width:22%" class="text-center">Amount</th>
                        <th style="width:10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty sessionScope.cart.items}">
                        <tr>
                            <td><h3>Cart is empty</h3></td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty sessionScope.cart.items}">
                        <c:forEach var="item" items="${sessionScope.cart.items}">
                            <tr>
                                <td data-th="Product">
                                    <div class="row">
                                        <div class="col-sm-2 hidden-xs"><img src="image/${item.id}.png" alt="book" width="100" class="img-responsive"></div>
                                        <div class="col-sm-10">
                                            <h4 class="nomargin">${item.name} ${item.number}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td data-th="Price">${item.price} $</td>
                                <td data-th="Quantity">
                                    <form action="cart">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="id" value="${item.id}">
                                        <input type="number" class="form-control text-center" name="quantity" id="quantity${item.number}" value="${item.quantity}" min="1" max="${item.bookByBookId.quantity}" onchange="checkQuantity(document.getElementById('quantity${item.number}').value, ${item.number}, 1, ${item.bookByBookId.quantity})">
                                    </form>
                                </td>
                                <td data-th="Subtotal" class="text-center">${item.price * item.quantity} $</td>
                                <td class="actions" data-th="">
                                    <button type="button" class="btn btn-info btn-sm" onclick="checkQuantity(document.getElementById('quantity${item.number}').value, ${item.number}, 1, ${item.bookByBookId.quantity})" title="Update"><i class="fa fa-edit"></i></button>
                                    <a href="cart?action=delete&id=${item.id}" onclick="return confirm('Do your want to remove this book?')" title="Delete"><button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
                <tfoot>
                    <tr class="visible-xs">
                        <td class="text-center"><strong>Total ${sessionScope.cart.totalAmount} $</strong></td>
                    </tr>
                    <tr>
                        <td><a href="home" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
                        <td colspan="2" class="hidden-xs"></td>
                        <td class="hidden-xs text-center"><strong>Total ${sessionScope.cart.totalAmount} $</strong></td>
                        <td>
                            <c:if test="${empty sessionScope.payment}">

                                <a href="pay" class="btn btn-success btn-block">Pay <i class="fa fa-angle-right"></i></a>

                            </c:if>
                            <c:if test="${not empty sessionScope.payment}">

                                <a href="pay" class="btn btn-success btn-block"onclick="return confirm('You paid for by this order. Do you want to pay again?')">Pay <i class="fa fa-angle-right"></i></a>

                            </c:if>
                        </td>
                    </tr>
                </tfoot>
            </table>
            <c:if test="${not empty sessionScope.payment}">
                <div class="alert alert-success">
                    <strong>Success!</strong> ${sessionScope.payment}! Go home to clear cart!
                </div>
            </c:if>
        </div>
        <script>
            function checkQuantity(quantity, number, min, max) {
                if (quantity < min || quantity > max) {
                    alert('Please enter quantity of range from ' + min + ' to ' + max + "!");
                } else {
                    document.forms[number].submit();
                }
            }
        </script>
    </body>
</html>
