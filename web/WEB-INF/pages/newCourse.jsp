<%@ page import="cLPackage.dataStore.Course" %>
<%@ page import="cLPackage.dataStore.Module" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

    String userId = (String)request.getParameter("userId").toString();
    String courseId = (String)request.getParameter("courseId").toString();
    List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
    Course course = null;
    for(int i = 0; i<courseList.size();i++){
        if(courseList.get(i).getId()==Long.parseLong(courseId)){
            course = courseList.get(i);
        }
    }
    Key<Course> courseKey = Key.create(Course.class,course.id);
    List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();
    System.out.println("list sfze +"+moduleList.size());

    String isPublished = (course.getIsPublic()==0)? "":"checked";
%>


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

    <title>New Course</title>
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
                    <li class="active"><a href="main">Homepage <span class="sr-only">(current)</span></a></li>
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
        <div class="input-group input-group-lg col-xs-5">
            <span class="input-group-addon" id="sizing-addon1">Course Name</span>
            <input type="text" class="form-control" placeholder="eg - CSE 215" aria-describedby="sizing-addon1" value="<%=course.getName()%>">

        </div>
        <br>
        <br>

        <div>
            Description:<br/><textarea rows="8" cols="50" name="course_description"><%=course.getDescription()%></textarea><br/><br/>
            Cover Image URL:<input type="text" name="course_img_url" value = "<%=course.getImgURL()%>"><br/><br/>
            Publish: <input type="checkbox" name="isPublished" style="width:20px; height: 20px;" <%=isPublished%>>
        </div>
        <hr/>
        <!---------!Modules ---------->
        <div class="input_fields_wrap">
            <label class="control-label">Add Modules</label>
            <span class="add_field_button" onclick="location.href='AddModuleServlet?userId=<%=userId%>&courseId=<%=courseId%>';">
                <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"></button>
            </span>
            <%--<button class="add_field_button">Add More Fields</button>--%>
            <div>
                <span class="btn glyphicon glyphicon-edit" title="edit"onclick="location.href='/newModule?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleList.get(0).id%>';"></span>
                Name:<input type="text" name="course_name" value = "<%=moduleList.get(0).getName()%>" disabled>
            </div>

            <!------  Start Dynamically loading ----------->
            <% for (int i = 1 ; i<moduleList.size();i++){%>
            <div>
                <span class="btn glyphicon glyphicon-edit" title="edit"
                onclick="location.href='/newModule?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleList.get(i).id%>';"></span>
                Name:<input type="text" name="course_name" value="<%=moduleList.get(i).getName()%>" disabled>
                <a href="#" class="remove_field"
                   onclick="location.href='/DeleteModuleServlet?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleList.get(i).id%>';">
                    <span class="remove_field glyphicon glyphicon-minus-sign"></span>
                </a>
            </div>
            <% }%>
            <!------ END Start Dynamically loading END----------->
        </div>

        <span class="input-group-btn" style="display: block; margin-top: 20px" title="Submit">
                <button class="btn btn-success glyphicon glyphicon-ok" type="button"
                        onclick="location.href='UpdateModuleServlet?userId=<%=userId%>&courseId=<%=courseId%>';">&nbsp;Submit</button>

                <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 20px;"
                        onclick="location.href='profile?userId=<%=userId%>';">&nbsp;Back</button>
        </span>
    </div>
</div>
<br/>
<br/>
</body>
</html>