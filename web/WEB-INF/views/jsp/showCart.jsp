<%-- 
    Document   : showCart
    Created on : Aug 31, 2020, 10:36:41 AM
    Author     : giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SHOW CART</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://code.jquery.com/jquery-3.3.1.slim.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>

        <style>
            body {
                background-color : gainsboro
            }
            #backToShopButton {
                position : relative;
                top : 0px;
                left: 125px ;
            }
            #checkOutButton {
                position : relative;
                top: 0px;
                left : 1000px;
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center">SHOW CART PAGE</h1>

        <div class="px-4 px-lg-0">
            <div class="pb-5">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                            <div class="table-responsive">
                                <a:if test="${not empty sessionScope.CART_OBJECT.customerCart}">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="p-2 px-3 text-uppercase">Item Name</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Color</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Category</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Quantity</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Update</div>
                                                </th>
                                                <th scope="col" class="border-0 bg-light">
                                                    <div class="py-2 text-uppercase">Remove</div>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <a:forEach var="cartItem" items="${sessionScope.CART_OBJECT.customerCart}">
                                                <form:form action="updateQuantityCartForm">
                                                    <tr class="border-0 align-middle">
                                                        <td>${cartItem.key.itemName}</td>
                                                        <td>${cartItem.key.color}</td>
                                                        <td>${cartItem.key.category}</td>
                                                        <td>
                                                            <input type="number" name="txtItemQuantity" value="${cartItem.value}" min="0" max="${cartItem.key.availableQuantity}"/>
                                                        </td>
                                                        <td>
                                                            <input type="hidden" name="txtItemID" value="${cartItem.key.itemID}" />
                                                            <input type="submit" name="btnAction" value="Update" />
                                                        </td>
                                                        <td>
                                                            <input type="hidden" name="txtItemID2" value="${cartItem.key.itemID}" />
                                                            <input type="submit" name="btnAction" value="Delete"/>
                                                        </td>
                                                    </tr>
                                                </form:form>
                                            </a:forEach> 
                                        </tbody>
                                    </table>
                                    <a:if test="${not empty requestScope.CHECK_OUT_ERROR}">
                                        <h2>
                                            ${requestScope.CHECK_OUT_ERROR}
                                        </h2>
                                    </a:if>
                                </a:if>
                                <a:if test="${empty sessionScope.CART_OBJECT.customerCart}">
                                    <h2>
                                        Your Cart is empty 
                                    </h2>
                                </a:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <a href="searchInfoForOtherRolesForm" id="backToShopButton">
            <button type="submit">
                <--- Back To Shop 
            </button>
        </a>
        <a:if test="${not empty sessionScope.CART_OBJECT.customerCart}">
            <a href="checkOutCartForm" id="checkOutButton">
                <button type="submit">
                    Check Out --->
                </button>
            </a>   
        </a:if>
    </body>
</html>
