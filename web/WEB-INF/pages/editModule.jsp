<%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 5/14/2017
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
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
    <script src="../../resources/js/newModuleController.js"></script>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
    <title>Edit Module</title>
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

        <!---------!Module Name ---------->
        <form action="/updateModule?moduleId=${moduleToEdit.id}" method="post">
            <div class="input-group input-group-lg col-xs-5">
                <span class="input-group-addon" id="sizing-addon1">Module Name</span>
                <%--<input id="module_name_field" type="text" class="form-control" value="<%=module.getName()%>" aria-describedby="sizing-addon1">--%>
                <input name="moduleEditName" id="module_name_field" type="text" class="form-control" value="${moduleToEdit.name}" aria-describedby="sizing-addon1">
            </div>
            <br>
            <br>
            <!---------!Topics ---------->
            <div class="input_fields_wrap">
                <label class="control-label">Add Topics</label>
                <span class="add_field_button">
                    <%--<button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"
                            onclick="location.href='/AddTopicServlet?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>';"></button>--%>
                    <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"
                            onclick="location.href='/newTopic?moduleId=${moduleToEdit.id}'"></button>
                </span>

                <!------  Start Dynamically loading ----------->
                <c:choose>
                    <c:when test="${fn:length(topicList) == 0}">
                        <h3>No Topics exist.</h3>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="topic" begin="0" items="${topicList}">
                            <div>
                                <span class="btn glyphicon glyphicon-edit" title="edit"
                                  onclick="location.href='/editTopic?topicId=${topic.id}'"></span>
                                Name:<input type="text" name="course_name" value="${topic.name}" disabled>
                                <a href="#" class="remove_field" onclick="location.href='/deleteTopic?topicId=${topic.id}'">
                                    <span class="remove_field glyphicon glyphicon-minus-sign"></span>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <!------ END Start Dynamically loading END----------->
            </div>
            <div class="checkbox">
                <label><input name="moduleEditHasTest" id="hasTest" type="checkbox" value="" ${moduleHasTest}>Test</label>
            </div>
            <span class="input-group-btn" style="display: block; margin-top: 20px" title="Submit">
                <button id="submit_btn" class="btn btn-success glyphicon glyphicon-ok" type="submit">&nbsp;Submit</button>

                <button class="btn btn-danger glyphicon glyphicon-remove" type="button"
                        onclick="location.href='/deleteModule?moduleId=${moduleToEdit.id}'">
                            &nbsp;Delete
                </button>

                <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 20px;"
                        onclick="location.href='/editCourse?courseId=${courseId}'">&nbsp;Back</button>
            </span>
        </form>
    </div>
</div>
</body>
</html>