<%-- 
    Document   : login
    Created on : Aug 4, 2020, 11:47:11 AM
    Author     : giang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <title>LOGIN PAGE</title>
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
            height: 455px ;
            width : 600px;
            margin : auto;
            text-align: center;

            position: relative;
        }
    </style>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.21/datatables.min.css"/>
    <body style="background-image: url('https://images.wallpaperscraft.com/image/books_library_shelves_138556_1920x1080.jpg')">
        <div class="container">
            <h1>
                LOGIN PAGE
            </h1>
            <form:form action="loginForm" method="POST">
                <input type="text" name="txtUsername" placeholder="Enter Email" required/>
                <br>
                <input type="password" name="txtPassword" placeholder="Enter Password" required/>
                <br>
                <br>
                <a:if test="${not empty requestScope.USER_LOGIN_ERROR}">
                    <font style="color :red">
                    ${requestScope.USER_LOGIN_ERROR}
                    </font>
                </a:if>
                <br>
                <div class="g-recaptcha" data-sitekey="6LeBjqgZAAAAAG14wvt37pFiG3dD1GOmUyr7W9wo" align="center">
                </div>
                <br>
                Don't have an account ?
                <a href="signUpPage">
                    Click here to create an account !!!
                </a>
                <br>
                <br>
                <input type="submit" name="buttonNames" value="Login" />
            </form:form>
            <br>
            <a href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:120/MVCSpring1/loginGoogleForm&response_type=code
               &client_id=354403101855-52ie7uvbs5qp2jlrnhd0medlnq0isjks.apps.googleusercontent.com&approval_prompt=force">
                <button type="submit" class="loginBtn loginBtn--google">
                    Login with Google
                </button>
            </a>
            <br>
            <a href="https://www.facebook.com/dialog/oauth?client_id=336830010667799&redirect_uri=http://localhost:120/MVCSpring1/loginFacebookForm">
                <button type="submit" class="loginBtn loginBtn--facebook">
                    Login with Facebook
                </button>
            </a>
        </div>
    </body>
</html>
