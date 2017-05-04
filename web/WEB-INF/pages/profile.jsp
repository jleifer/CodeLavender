<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%@ page import="cLPackage.dataStore.*" %><%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    //register classes first
    ObjectifyService.register(User.class);
    ObjectifyService.register(Course.class);
    ObjectifyService.register(Module.class);
    ObjectifyService.register(Topic.class);
    ObjectifyService.register(MultipleChoices.class);

    String Userid = (String)request.getParameter("userId");
    List<User> userList = ObjectifyService.ofy().load().type(User.class).list();
    User curUser = null;
    for(int i = 0; i<userList.size();i++){
        if(userList.get(i).getId()==Long.parseLong(Userid)){
            curUser = userList.get(i);
        }
    }
    Key<User> userKey = Key.create(User.class,curUser.id);
    List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).ancestor(userKey).list();
    System.out.println("list sfze +"+courseList.size());
%>
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
                <a href="main?email=${email}"><img src="../../resources/img/dev.png" alt="*Logo*" height = "50px" width = "75px" ></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="main?email=${email}">Homepage <span class="sr-only">(current)</span></a></li>
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
        <!----new course button---->
        <div align="right">
            <button type="button" class="btn btn-outline-primary"
                    onclick="location.href = 'AddModuleServlet?courseId=-1&userId=<%=request.getAttribute("userId")%>';">
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
        </div>

        <div style="width:600px; height:800px; float: left; border: 1px solid lightgrey; margin-left: 30px;">
            &nbsp;<h2>Course Created</h2>
            <hr />
            <!--------- Start Dyanamic generating ------------>
            <% for (int i =0; i<courseList.size();i++){
                    String visibility = " (Not Published)";
                    if(courseList.get(i).getIsPublic()==1){
                        visibility = "";
                    }
            %>
            <div class="courseCreated">
                <span onclick="location.href = 'newCourse?courseId=<%=courseList.get(i).id%>&userId=<%=request.getAttribute("userId")%>';">
                    <%=courseList.get(i).getName()+visibility%>
                </span>
            </div>
            <% }%>
            <!----------------END generating ------------------->
        </div>
    </div>
</div>
</body>
</html>