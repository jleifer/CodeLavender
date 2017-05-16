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
    <script src="../../resources/js/googleLogin.js"></script>
    <script src="../../resources/js/courseSearch.js"></script>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>


    <title>DevRoot</title>

</head>
<body onload="loadAuth()">
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




<div class="page-tab">
    <div class="page-tab-all-div">
        <div class="page-single-table" onclick="location.href='main'">
            All
        </div>
        <div class="page-single-table" onclick="location.href='InstructorCourse'">
            Prof's Classes
        </div>
        <div class="page-single-table" onclick="location.href='UserCourse'">
            Users' Classes
        </div>

        <!--search bar-->
        <div class="row" style="width: 650px; float:left;margin-left:40px; margin-top:2px;">
            <div class="col-lg-6">
                <div class="input-group" >
                    <form action="/search" method="get">
                        <input id="searchBox" type="text" name="searchStr" class="form-control" placeholder="Search by Course Name..."
                               style=" width:240px; ">
                        <span class="input-group-btn"><input id="GoButooon" class="btn btn-default" type="submit" value="Go!"></span>
                    </form>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->
    </div>

</div>





<div class = "main-page-all-recommendation-div">
    <!-- recommendation Professors -->
    <br style="clear: both;">
    <div class="main-page-all-recommendation-name">Best Classes by Professors</div>
    <div class="rec-see-all"><a href="/InstructorCourse">See All</a></div>
    <br style="clear: both;">

    <!----------- Dynamically change ------------->
    <!------- load courses here ---------->
    <c:if test="${fn:length(instructorCourseList)==0}">
        <h3 style="color: red;">No Course Yet...</h3>
    </c:if>
    <c:forEach var="course" begin="0" items="${instructorCourseList}">
        <div class="main-page-single-rec" onclick="location.href='viewCourse?userId=${userId}&courseId=${course.id}&curUserId=${course.theParentUser.id}'">
            <img src="../../resources/img/rec-img.jpeg" alt="course" style="width:219px;">
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
        </div>
    </c:forEach>
    <!------------- END Dynamically generate here END ----------------------->

    <!--
    <div class="main-page-single-rec">
        <img src="../../resources/img/rec-img.jpeg" alt="course" style="width:219px;">
        <div class="rec-class-name">Boolean Logic for Beginners</div>
        <div class="rec-creator-name">Prof.Jack Smith</div>
        <div class="rec-class-intro">This course is designed for beginners with an apprehensive approach to teach Boolean
            Logic...
        </div>
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
        <div class="progress"><div class="progress_unfill"></div><div class="progress_minMark"></div></div>
        <div class="progress-text">25%</div>
    </div>
    -->
    <br style="clear:both;" />

    <!--  User Recommendations  -->
    <div class="main-page-all-recommendation-name">Best Classes by Users</div>
    <div class="rec-see-all"><a href="/UserCourse">See All</a></div>
    <br style="clear: both;">


    <!----------- Dynamically change ------------->
    <!------- load courses here ---------->
    <c:if test="${fn:length(userCourseList)==0}">
        <h3 style="color: red;">No Course Yet...</h3>
    </c:if>
    <c:forEach var="course" begin="0" items="${userCourseList}">
        <div class="main-page-single-rec" onclick="location.href='viewCourse?userId=${userId}&courseId=${course.id}&curUserId=${course.theParentUser.id}'">
            <img src="../../resources/img/rec-img2.jpeg" alt="course" style="width:219px;">
            <div class="rec-class-name">${course.name}</div>
            <div class="rec-creator-name">${course.ownerFirst} ${course.ownerLast}</div>
            <div class="rec-class-intro">${course.description}</div>
            <%--<span class="glyphicon glyphicon-star"
                  aria-hidden="true" style="margin-left:10px; color:red;"></span>
            <span class="glyphicon glyphicon-star"
                  aria-hidden="true" style=" color:red;"></span>
            <span class="glyphicon glyphicon-star"
                  aria-hidden="true" style="color:red;"></span>
            <span class="glyphicon glyphicon-star"
                  aria-hidden="true" style=" color:red;"></span>
            <span class="glyphicon glyphicon-star"
                  aria-hidden="true" style=" color:lavender;"></span>--%>

            <span class="rec-rating-text">${course.endorsedByUsers} <span style="color:grey;">(${course.totalEndorsers})</span></span>
            <div class="progress-text">Start Now</div>
        </div>
    </c:forEach>
    <!------------- END Dynamically generate here END ----------------------->


    <br style="clear:both;" />
    <br><br><br><br><br><br>
</div>
</body>
</html>