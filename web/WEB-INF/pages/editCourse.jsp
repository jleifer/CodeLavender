<%--
  Created by IntelliJ IDEA.
  User: Konstantinos
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

    <link rel="stylesheet" href="../../resources/css/profile.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="../../resources/js/jquery-3.1.1.js"></script>
    <script src="../../resources/js/bootstrap.js"></script>
    <script src="../../resources/js/googleLogIn.js"></script>
    <script src="../../resources/js/newCourseController.js"></script>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>

    <title>Edit Course</title>
</head>
<body>

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

        <!---------!Course Name ---------->
        <form action="/updateCourse?courseId=${courseToEdit.id}" method="post">
            <div class="input-group input-group-lg col-xs-5">
                <span class="input-group-addon" id="sizing-addon1">Course Name</span>
                <input name="courseEditName" id="course_name_field" type="text" class="form-control" placeholder="eg - CSE 215" aria-describedby="sizing-addon1" value="${courseToEdit.name}">

            </div>
            <br>
            <br>

            <div>
                Description:<br/><textarea id="course_description" rows="8" cols="50" name="courseEditDescription">${courseToEdit.description}</textarea><br/><br/>
                Cover Image URL:<input id="img_url" type="text" name="courseEditImgURL" value = "${courseToEdit.imgURL}"><br/><br/>
                Publish: <input id="isPublic" type="checkbox" name="courseEditIsPublic" style="width:20px; height: 20px;" ${coursePublished}>
            </div>
            <hr/>
            <!---------!Modules ---------->
            <div class="input_fields_wrap">
                <label class="control-label">Add Modules</label>
                <%-- To add modules and navigate back to current course edit page --%>
                <span class="add_field_button" onclick="location.href='/newModule?courseId=${courseToEdit.id}'">
                    <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"></button>
                </span>

                <!------  Start Dynamically loading ----------->
                <c:forEach var="module" begin="0" items="${moduleList}">
                    <div>
                    <span class="btn glyphicon glyphicon-edit" title="edit"
                          onclick="location.href='/editModule?moduleId=${module.id}'"></span>
                        Name:<input type="text" name="course_name" value="${module.name}" disabled>
                        <%--<a href="#" class="remove_field" onclick="location.href='/DeleteModuleServlet?courseId=${courseToEdit.id}&moduleId=${module.id}'">--%>
                        <a href="#" class="remove_field" onclick="location.href='/deleteModule?moduleId=${module.id}'">
                            <span class="remove_field glyphicon glyphicon-minus-sign"></span>
                        </a>
                    </div>
                </c:forEach>
                <!------ END Start Dynamically loading END----------->
            </div>

            <span class="input-group-btn" style="display: block; margin-top: 20px" title="Submit">
                <button class="btn btn-success glyphicon glyphicon-ok" type="submit"
                        id ="submit_btn">&nbsp;Submit
                </button>

                <button class="btn btn-danger glyphicon glyphicon-remove" type="button"
                        onclick="location.href='/deleteCourse?courseId=${courseToEdit.id}'">
                        &nbsp;Delete
                </button>

                <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 20px;"
                        onclick="location.href='profile';">&nbsp;Back
                </button>
            </span>
        </form>


    </div>
</div>
<br/>
<br/>
</body>
</html>