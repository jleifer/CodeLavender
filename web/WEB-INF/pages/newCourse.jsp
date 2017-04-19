<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">
    <title>New Course</title>
</head>
<body>
<link rel="stylesheet" href="../../resources/css/profile.css">
<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../../resources/css/newCourse-style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="../../resources/js/jquery-3.1.1.js"></script>
<script src="../../resources/js/bootstrap.js"></script>
<script src="../../resources/js/googleLogIn.js"></script>
<script src="../../resources/js/newCourseController.js"></script>
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
                <a href="hello"><img src="../../resources/img/dev.png" alt="*Logo*" height = "50px" width = "75px" ></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="hello">Homepage <span class="sr-only">(current)</span></a></li>
                </ul>
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
        <div class = content>
            <!---------!Course Name ---------->
            <div class="input-group input-group-lg col-xs-5">
                <span class="input-group-addon" id="sizing-addon1">Course Name</span>
                <input type="text" class="form-control" placeholder="eg - CSE 215" aria-describedby="sizing-addon1">
                <span class="input-group-btn">
                    <%--<button class="btn btn-success glyphicon glyphicon-ok" type="button"></button>--%>
                </span>
            </div>
            <br>
            <br>
            <!---------!Modules ---------->
            <div class="input_fields_wrap">
                <label class="control-label">Add Modules</label>
                <span class="add_field_button">
                    <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"></button>
                </span>
                <%--<button class="add_field_button">Add More Fields</button>--%>

                <input type="text" placeholder="Module Name" name="mod_name[]">
                <button class="btn btn-warning glyphicon glyphicon-pencil btn-xs" type="button"></button>
            </div>


            <div class="row">
                <div class="col-lg-6">
                    <div class="input-group">
                        <%--<span class="input-group-addon">Module #</span>--%>
                        <input type="text" class="form-control" size="4">
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <%--<div class="input-group-btn">--%>
                        <%--<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                        <%--Action--%>
                        <%--</button>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>



    </div>
</div>
</body>
</html>