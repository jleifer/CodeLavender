<%@ page import="cLPackage.dataStore.Course" %>
<%@ page import="cLPackage.dataStore.Module" %>
<%@ page import="cLPackage.dataStore.Topic" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Jonathan
  Date: 4/21/2017
  Time: 12:19 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- JSP Taglib -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ObjectifyService.register(Course.class);
    ObjectifyService.register(Module.class);
    ObjectifyService.register(Topic.class);
    String userId = request.getParameter("userId").toString();
    String courseId = request.getParameter("courseId").toString();
    List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
    Course course = null;
    for(int i = 0; i<courseList.size();i++){
        if(courseList.get(i).getId()==Long.parseLong(courseId)){
            course = courseList.get(i);
        }
    }
    Key<Course> courseKey = Key.create(Course.class,Long.parseLong(courseId));
    List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();
    System.out.println("How many Modules: "+moduleList.size());
    request.setAttribute("moduleList",moduleList);
    request.setAttribute("course",course);

    ArrayList<List<Topic>> topicList = new ArrayList<List<Topic>>(moduleList.size());
    for(int i = 0; i<moduleList.size();i++){
        Key<Module> moduleKey = Key.create(Module.class, moduleList.get(i).getId());
        List<Topic> topics = ObjectifyService.ofy().load().type(Topic.class).ancestor(moduleKey).list();
        topicList.set(i,topics);
    }
    request.setAttribute("topicList",topicList);
    System.out.println("How Many here: "+topicList.size());
%>
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
<!----------- Navbar End ------------>
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


    <div id="course-page-name"><h1><c:out value="${course.name}"></c:out></h1></div>
    <c:forEach items="${moduleList}" var="module" varStatus="moduleIndex">
        <div class="module_active">
            <div class="module-name"><h3><c:out value="${module.name}"></c:out></h3></div>
            <hr/>

            <c:forEach items="${topicList.get(0)}" var="topic" varStatus="topicIndex">
                <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
                <div class="topic">
                    <div class="topic-name"><a href="#">Topic <c:out value="${topicIndex + 1}"></c:out></a></div>
                        <div class="score"><span class="passed">100%</span></div>
                    <c:out value="${topic.name}"></c:out>
                    <br/>
                </div>
                <br/>
            </c:forEach>

            <hr />
            <div class="topic-name"><a href="#" style="margin-left: 30px;">Module Test</a></div>
            <div class="score"><span class="passed" style="margin-right: 30px;">90%</span></div>
        </div>
    </c:forEach>

    <!-------

    <div id="course-page-name"><h1>Sample Course</h1></div>

    <div class="module_active">
        <div class="module-name"><h3>Module 1</h3></div>

        <hr/>

        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 1</a></div>
            <div class="score"><span class="passed">100%</span></div>
            Test Name
            <br/>
        </div>
        <br/>

        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 2</a></div>
            <div class="score"><span class="passed">100%</span></div>

            <br/>
        </div>
        <br/>
        <div class="check-sign"><span class="glyphicon glyphicon-ok-sign"></span></div>
        <div class="topic">
            <div class="topic-name"><a href="#">Topic 3</a></div>
            <div class="score"><span class="passed">80%</span></div>

            <br/>
        </div>
        <br/>


        <hr />
        <div class="topic-name"><a href="#" style="margin-left: 30px;">Module Test</a></div>
        <div class="score"><span class="passed" style="margin-right: 30px;">90%</span></div>
        <br/>
    </div>
 -->

</div>


</body>
</html>