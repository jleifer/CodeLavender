<%--
  Created by IntelliJ IDEA.
  User: Yifang Cao
  Date: 5/15/2017
  Time: 2:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- JSP Taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin User Management</title>
    <meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Javascript -->
    <script src="../../resources/js/jquery-3.1.1.min.js"></script>
    <script src="../../resources/js/bootstrap.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="../../resources/js/googleLogin.js" async defer></script>
    <meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">
    <link rel="stylesheet" href="../../resources/css/bootstrap.css">
    <!-- Start our file -->
    <script src="../../resources/js/adminUserManagement.js"></script>
    <link rel="stylesheet" href="../../resources/css/adminUserManagement.css">
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
</head>
<body>

<!------------- Navbar -------------->
<nav class="navbar navbar-inverse bs-dark">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="main"><img src="../../resources/img/dev.png" alt="*Logo*" height = "50px" width = "75px" ></a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="main">Homepage <span class="sr-only">(current)</span></a></li>
        </ul>
        <!--<form class="navbar-form navbar-left form-horizontal" role="search">-->
        <!--<div class="input-group">-->
        <!--<input type="text" class="search-box" placeholder="Search">-->
        <!--<button type="submit" class="btn"><span class="glyphicon glyphicon-search"></span></button>-->
        <!--</div>-->
        <!--</form>-->
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle navbar-img" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    Account
                    <img class="profImg" src="http://placehold.it/150x150" class="img-circle" alt="Profile Image" />
                </a>
                <ul class="dropdown-menu">
                    <li><a href="profile">Profile</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a onclick="signOut();">Sign Out</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<!----------- !Navbar End ------------>

<!------------- User management -------------->


<div class="management_div">
    <h1>Admin User Management</h1>
    <hr />
    <div class="user_roster">
        <c:forEach items="${userList}" var="user">
            <%-- Prepare data before generating --%>
            <c:if test="${user.isInstructor==1}">
                <c:set var="user_status" value=" (Instructor)" scope="page"/>
                <c:url value="adminMakeInstructor" var="adminMakeInstructor" scope="page">
                    <c:param name="isInstructor" value="0"/>
                </c:url>
            </c:if>
            <c:if test="${user.isInstructor!=1}">
                <c:set var="user_status" value=" (Non-Instructor)" scope="page"/>
                <c:url value="adminMakeInstructor" var="adminMakeInstructor" scope="page">
                    <c:param name="isInstructor" value="1"/>
                </c:url>
            </c:if>
            <%-- Prepare data END--%>
            <h3 class="user_entry" onclick="location.href='${adminMakeInstructor}&userId=${user.id}'">
                <c:out value="${user.firstName} "/>
                <c:out value="${user.lastName}"/>
                <c:out value="[${user.email}] "/>
                <span style="float: right; color:red; font-size:14px;">
                    <c:out value="${user_status}"/>
                </span>
            </h3>
        </c:forEach>
    </div>
</div>
<!------------- User management END-------------->

</body>
</html>
