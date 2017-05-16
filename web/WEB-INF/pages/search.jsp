<%--
  Created by IntelliJ IDEA.
  User: Randhawa
  Date: 4/21/17
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Search Results</title>
    <meta name="google-signin-client_id" content="960219417263-di4ik7aduhjj4i9ulc5fjfcskjc2puj6.apps.googleusercontent.com">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Javascript -->
    <script src="../../resources/js/jquery-3.1.1.min.js"></script>
    <script src="../../resources/js/bootstrap.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="../../resources/js/googleLogin.js" async defer></script>
    <link rel="stylesheet" href="../../resources/css/bootstrap.css">
    <!-- Start our file -->
    <script src="../../resources/js/googleLogin.js"></script>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>


    <title>DevRoot</title>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
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

<div style=" width:600px; margin:auto;">
    <h1>Search Results "${searchStr}"</h1>
    <br style="clear: both;">
    <c:choose>
        <c:when test="${fn:length(courseList) == 0}">
            <h1>No Results Found</h1>
        </c:when>
        <c:otherwise>
            <c:forEach var="course" begin="0" items="${courseList}">
                <div class="main-page-single-rec" onclick="location.href='viewCourse?userId=${userId}&courseId=${course.id}'">
                    <img src="../../resources/img/rec-img2.jpeg" alt="course" style="width:219px;">
                    <div class="rec-class-name">${course.name}</div>
                    <div class="rec-creator-name">${course.ownerFirst} ${course.ownerLast}</div>
                    <div class="rec-class-intro">${course.description}</div>

                    <span class="glyphicon glyphicon-star"
                          aria-hidden="true" style="margin-left:10px; color:red;"></span>
                    <span class="glyphicon glyphicon-star"
                          aria-hidden="true" style=" color:red;"></span>
                    <span class="glyphicon glyphicon-star"
                          aria-hidden="true" style="color:red;"></span>
                    <span class="glyphicon glyphicon-star"
                          aria-hidden="true" style=" color:red;"></span>
                    <span class="glyphicon glyphicon-star"
                          aria-hidden="true" style=" color:lavender;"></span>
                    <span class="rec-rating-text">3.9 <span style="color:grey;">(852)</span></span>
                    <div class="progress-text">Start Now</div>
                    <br style="clear:both;" />
                    <br/><br/><br/><br/><br/><br/>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>