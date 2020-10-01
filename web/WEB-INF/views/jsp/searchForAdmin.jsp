<%-- 
    Document   : search
    Created on : Aug 4, 2020, 12:05:06 PM
    Author     : giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCH PAGE</title>
    </head>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.css"/>

    <body>
        <div class="container">
            <a:if test="${not empty sessionScope.USER_INFO}">
                <form action="logOutForm">
                    <input type="submit" value="Log Out" />
                    <br>
                    <br>
                </form>
                <h1>WELCOME TO SEARCH PAGE, Manager ${sessionScope.USER_INFO.fullname}</h1>
                <form action="searchInfoForm">
                    Item Name : <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
                    <br/>
                    <br/>
                    Color :
                    <select name="txtColorName">
                        <option></option>
                        <a:forEach var="colorEl" items="${sessionScope.COLOR_LIST}">
                            <option value="${colorEl}" ${param.txtColorName == colorEl ? 'selected' : ''}>
                                ${colorEl}
                            </option>
                        </a:forEach>
                    </select>
                    <br>
                    <br>
                    <input type="submit" value="Search Item" />
                    <br>
                    <br>
                    <script>
                        if (window.history.replaceState) {
                            window.history.replaceState(null, null, window.location.href);
                        }
                    </script>
                    <a:if test="${not empty param.txtSearchValue || not empty param.txtColorName}">
                        <a:if test="${not empty sessionScope.RESOURCE_LIST}">
                            <table border="1" id="datatable" class="table table-striped table-bordered">
                                <thead>
                                    <tr class="thead-dark">
                                        <th>
                                            Item Name
                                        </th>
                                        <th>
                                            Color
                                        </th>
                                        <th>
                                            Category
                                        </th>
                                        <th>
                                            Available Quantity
                                        </th>
                                        <th>
                                            Using Date
                                        </th>
                                        <th>
                                            End Date
                                        </th>
                                        <th>
                                            Privacy Status
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <a:forEach var="itemElement" items="${sessionScope.RESOURCE_LIST}">
                                        <tr>
                                            <td>${itemElement.itemName}</td>
                                            <td>${itemElement.color}</td>
                                            <td>${itemElement.category}</td>
                                            <td>${itemElement.availableQuantity}</td>
                                            <td>${itemElement.usingDate}</td>
                                            <td>${itemElement.endDate}</td>
                                            <td>${itemElement.privacyName}</td>

                                        </tr>
                                    </a:forEach>    
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <a:set var="numOfPages" value="${sessionScope.NUM_OF_PAGES}"/>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <a:forEach var="pageNumber" begin="1" end="${numOfPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="searchInfoForm?txtSearchValue=${param.txtSearchValue}&txtColorName=${param.txtColorName}&page=${pageNumber}">
                                                ${pageNumber}
                                            </a>
                                        </li>
                                    </a:forEach>
                                </ul>
                            </nav>
                        </a:if>
                        <a:if test="${empty sessionScope.RESOURCE_LIST}">
                            <h2>
                                The list is empty
                            </h2>
                        </a:if>
                    </a:if>
                </form>
            </a:if>
            <a:if test="${empty sessionScope.USER_INFO}">
                <a:redirect url="loginPage"/>
            </a:if>
        </div>
        <%--
                <script src="https://unpkg.com/jquery@3.2.1/dist/jquery.min.js"></script>
                <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.js"></script>
                <script>
                    $(document).ready(function () {
                        $("#datatable").DataTable();
                    })
                </script>
        --%>
    </body>
</html>
