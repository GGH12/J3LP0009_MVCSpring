<%-- 
    Document   : confirmCode
    Created on : Sep 17, 2020, 10:46:18 AM
    Author     : Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="a"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CONFIRM PAGE</title>
    </head>
    <body>
        <h1>Confirm Code</h1>
        <a:if test="${not empty sessionScope.CONFIRM_CODE}">
            <form action="confirmCodeForm" method="POST">
                Confirm Code : <input type="password" name="txtConfirmCode" value="${param.txtConfirmCode}" />
                <br>
                <br>
                <input type="submit" value="Cofirm" />
            </form>    
        </a:if>
        <a:if test="${empty sessionScope.CONFIRM_CODE}">
            <a:redirect url="/"/>
        </a:if>
    </body>
</html>
