<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 4/21/2017
  Time: 12:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="../../resources/css/course.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>

    <title>DevRoot</title>
</head>
<body>
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
                    <li><a href="profile?userId=<%=request.getAttribute("userId")%>">Profile</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a onclick="signOut();">Sign Out</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<!----------- !Navbar End ------------>

<div class="course-page-all">
    <div id="course-page-name"><h1>Sample Course</h1></div>

    <div class="module_active">
        <div style="width: 100px; margin:auto; "><h3>Module 1</h3></div>

        <hr/>

        <!--<div class="check_bar"></div>-->
        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 1</a></div>
            <div class="score"><span class="passed">100%</span></div>

            <br style="clear: both;" />
            <div style="margin-top: 10px;">This topic will cover basic Java syntax, and some other material</div>
        </div>
        <br style="clear: both;" />

        <!--<div class="check_bar"></div>-->
        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 2</a></div>
            <div class="score"><span class="passed">100%</span></div>

            <br style="clear: both;" />
            <div style="margin-top: 10px;">This topic will cover basic Java syntax, and some other material</div>
        </div>
        <br style="clear: both;" />
        <!--<div class="check_bar"></div>-->
        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 3</a></div>
            <div class="score"><span class="passed">80%</span></div>

            <br style="clear: both;" />
            <div style="margin-top: 10px;">This topic will cover basic Java syntax, and some other material</div>
        </div>
        <br style="clear: both;" />


        <hr />
        <div class="topic-name"><a href="#" style="margin-left: 30px;">Module Test</a></div>
    <div class="score"><span class="passed" style="margin-right: 30px;">90%</span></div>
    <br style="clear: both;" />
</div>

</div>


</body>
</html>
