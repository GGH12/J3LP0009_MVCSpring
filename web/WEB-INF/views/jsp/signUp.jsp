<%-- 
    Document   : signUp
    Created on : Aug 20, 2020, 3:57:01 PM
    Author     : giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIGN UP PAGE</title>
    </head>
    <style>
        body { padding: 2em; }
        .loginBtn {
            box-sizing: border-box;
            position: relative;
            /* width: 13em;  - apply for fixed size */
            margin: 0.2em;
            padding: 0 15px 0 46px;
            border: none;
            text-align: left;
            line-height: 34px;
            white-space: nowrap;
            border-radius: 0.2em;
            font-size: 16px;
            color: #FFF;
            width : 300px;
        }
        .loginBtn:before {
            content: "";
            box-sizing: border-box;
            position: absolute;
            top: 0;
            left: 0;
            width: 34px;
            height: 100%;
        }
        .loginBtn:focus {
            outline: none;
        }
        .loginBtn:active {
            box-shadow: inset 0 0 0 32px rgba(0,0,0,0.1);
        }


        /* Facebook */
        .loginBtn--facebook {
            background-color: #4C69BA;
            background-image: linear-gradient(#4C69BA, #3B55A0);
            /*font-family: "Helvetica neue", Helvetica Neue, Helvetica, Arial, sans-serif;*/
            text-shadow: 0 -1px 0 #354C8C;
        }
        .loginBtn--facebook:before {
            border-right: #364e92 1px solid;
            background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_facebook.png') 6px 6px no-repeat;
        }
        .loginBtn--facebook:hover,
        .loginBtn--facebook:focus {
            background-color: #5B7BD5;
            background-image: linear-gradient(#5B7BD5, #4864B1);
        }
        .loginBtn--google {
            /*font-family: "Roboto", Roboto, arial, sans-serif;*/
            background: #DD4B39;
        }
        .loginBtn--google:before {
            border-right: #BB3F30 1px solid;
            background: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/14082/icon_google.png') 6px 6px no-repeat;
        }
        .loginBtn--google:hover,
        .loginBtn--google:focus {
            background: #E74B37;
        }
        div.container {
            color : white;
            background-color: black;
            opacity: 0.81;
            height: 500px ;
            width : 600px;
            margin : auto;
            text-align: center;
            position: relative;
        }
        font.red-text {
            color : #BB3F30;
        }
    </style>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.css"/>
    <body style="background-image: url('https://images.wallpaperscraft.com/image/books_library_shelves_138556_1920x1080.jpg') ">
        <div class="container">
            <h1>CREATE ACCOUNT</h1>
            <form action="signUpForm" method="POST">
                Email Address: <input type="email" name="txtEmailAddressValue" value="${param.txtEmailAddressValue}" required/>
                <br>
                <a:if test="${not empty requestScope.EMAIL_ADDRESS}">
                    <font class="red-text">
                    ${requestScope.EMAIL_ADDRESS}    
                    </font>
                </a:if>
                <br>
                Password: <input type="password" name="txtPasswordValue" value="${param.txtPasswordValue}"/>
                <br>
                <a:if test="${not empty requestScope.PASSWORD}">
                    <font class="red-text">
                    ${requestScope.PASSWORD}    
                    </font>
                </a:if>
                <br>
                Confirm Password: <input type="password" name="txtConfirmPasswordValue" value="${param.txtConfirmPasswordValue}" />
                <br> 

                <a:if test="${not empty requestScope.CONFIRM_PASSWORD}">
                    <font class="red-text">
                    ${requestScope.CONFIRM_PASSWORD}
                    </font>
                </a:if>
                <br>
                Full Name : <input type="text" name="txtFullnameValue" value="${param.txtFullnameValue}"/>
                <br>

                <a:if test="${not empty requestScope.FULL_NAME}">
                    <font class="red-text">
                    ${requestScope.FULL_NAME}
                    </font>
                </a:if>
                <br>
                Home address :<input type="text" name="txtHomeAdressValue" value="${param.txtHomeAdressValue}"/>

                <br>
                <a:if test="${not empty requestScope.ADDRESS}">
                    <font class="red-text">
                    ${requestScope.ADDRESS}
                    </font>
                </a:if>
                <br>
                Phone Number : <input type="text" name="txtPhoneNumberValue" value="${param.txtPhoneNumberValue}"/>
                <br>

                <a:if test="${not empty requestScope.PHONE_NUMBER}">
                    <font class="red-text">
                    ${requestScope.PHONE_NUMBER}    
                    </font>
                </a:if>
                <br>
                <input type="submit" value="Register Account" />
            </form>
        </div>
    </body>
</html>
