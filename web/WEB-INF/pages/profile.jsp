<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">
    <title>Profile</title>
</head>
<body>
<link rel="stylesheet" href="../../resources/css/profile.css">
<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="../../resources/js/jquery-3.1.1.js"></script>
<script src="../../resources/js/bootstrap.js"></script>
<script src="../../resources/js/googleLogIn.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
<div class="mainbody container-fluid">
    <div class="row">
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
                    <li class="active"><a href="main">Homepage</a></li>
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
        <!----new course button---->
        <div align="right">
            <button class="btn btn-outline-primary" onclick="location.href='/newCourse'">
                <span class="glyphicon glyphicon-plus"></span> Add course
            </button>
        </div>
        <!----Profile Div---->
        <div style="padding-top:50px;"> </div>

        <div class="col-lg-3 col-md-3 hidden-sm hidden-xs" style=" float: left;">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="media">
                        <div align="center">
                            <img class="profImg thumbnail img-responsive" src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" width="300px" height="300px">
                        </div>
                        <c:if test="${isInstrucotr==true}">
                            <div style="color:red;width:80px;margin:auto;">*Instructor*</div>
                        </c:if>
                        <div class="media-body">
                            <hr>
                            <h3><strong>Last Name</strong></h3>
                            <p id = "LastName">Last Name</p>
                            <hr>
                            <h3><strong>First Name</strong></h3>
                            <p id = "FirstName">First Name</p>
                            <hr>
                            <h3><strong>Email</strong></h3>
                            <p id = "Email">email@email.com</p>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-outline-primary"
                    onclick="location.href='MailServlet'">
                <span class="glyphicon glyphicon-plus"></span> Request prof status
            </button>
        </div>

        <div style="width:600px; float: left; border: 1px solid lightgrey; margin-left: 30px; padding-bottom: 1%; padding-left: 1%;">
            <h2>Course Created</h2>
            <hr />
            <!--------- Start Dyanamic generating ------------>
            <c:choose>
                <c:when test="${fn:length(courseList) == 0}">
                    <h3>(No Courses Created Yet)</h3>
                </c:when>
                <c:otherwise>
                    <c:forEach var="course" begin="0" items="${courseList}">
                        <div class="courseCreated">
                            <span onclick="location.href = 'editCourse?courseId=${course.id}'">
                                ${course.name}
                                <c:if test="${course.isPublic != 1}">
                                    (Not Published)
                                </c:if>
                            </span>
                            <button class="btn btn-danger glyphicon glyphicon-remove"
                                    onclick="location.href='/deleteCourse?courseId=${course.id}'">
                                Delete
                            </button>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <!----------------END generating ------------------->
        </div>
        <div style="width:600px; height:auto; margin-top: 30px; padding-bottom: 20px; border: 1px solid lightgrey; margin-left: 10px;">
            &nbsp;<h2>Course Started</h2>
            <hr />
            <!--------- Start Dyanamic generating ------------>
            <c:forEach var="course" begin="0" items="${courseStarted}">
                <div class="courseCreated">
                        <span onclick="location.href = 'viewCourse?courseId=${course.id}&userId=${userId}'">
                                ${course.name}
                        </span>
                </div>
            </c:forEach>
            <!----------------END generating ------------------->
        </div>
    </div>
</div>
</body>
</html>