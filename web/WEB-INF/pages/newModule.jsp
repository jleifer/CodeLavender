<%@ page import="cLPackage.dataStore.Course" %>
<%@ page import="cLPackage.dataStore.Module" %>
<%@ page import="cLPackage.dataStore.Topic" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Konstantinos
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String userId = (String)request.getParameter("userId").toString();
    String courseId = (String)request.getParameter("courseId").toString();
    String moduleId = (String)request.getParameter("moduleId").toString();
    System.out.println(userId+","+courseId+","+moduleId);
    Key<Course> courseKey = Key.create(Course.class,Long.parseLong(courseId));
    List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();
    Module module = null;
    for(int i = 0; i<moduleList.size();i++){
        if(moduleList.get(i).getId()==Long.parseLong(moduleId)){
            module = moduleList.get(i);
        }
    }
    Key<Module> moduleKey = Key.create(Module.class,Long.parseLong(moduleId));
    List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).ancestor(moduleKey).list();
    System.out.println("Topic List size: "+topicList.size());
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
    <script src="../../resources/js/newModuleController.js"></script>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
    <title>New Module</title>
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
                <a href="main?email=<%=request.getAttribute("email")%>"><img src="../../resources/img/dev.png" alt="*Logo*" height = "50px" width = "75px" ></a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="main?email=<%=request.getAttribute("email")%>">Homepage <span class="sr-only">(current)</span></a></li>
                </ul>
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

        <!---------!Module Name ---------->
        <div class="input-group input-group-lg col-xs-5">
            <span class="input-group-addon" id="sizing-addon1">Module Name</span>
            <input id="module_name_field" type="text" class="form-control" value="<%=module.getName()%>" aria-describedby="sizing-addon1">
        </div>
        <br>
        <br>
        <!---------!Topics ---------->
        <div class="input_fields_wrap">
            <label class="control-label">Add Topics</label>
            <span class="add_field_button">
                <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"
                        onclick="location.href='/AddTopicServlet?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>';"></button>
            </span>
            <%--<button class="add_field_button">Add More Fields</button>--%>
            <div>
                <span class="btn glyphicon glyphicon-edit" title="edit"
                      onclick="location.href='/newTopic?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>&topicId=<%=topicList.get(0).id%>';"></span>
                Name:<input type="text" name="module_name" value="<%=topicList.get(0).getName()%>" disabled/>
            </div>
            <!------  Start Dynamically loading ----------->
            <% for (int i = 1 ; i<topicList.size();i++){%>
            <div>
                <span class="btn glyphicon glyphicon-edit" title="edit"
                      onclick="location.href='/newTopic?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>&topicId=<%=topicList.get(i).id%>';"></span>
                Name:<input type="text" name="course_name" value="<%=topicList.get(i).getName()%>" disabled>
                <a href="#" class="remove_field"
                   onclick="location.href='/DeleteTopicServlet?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>&topicId=<%=topicList.get(i).id%>';">
                    <span class="remove_field glyphicon glyphicon-minus-sign"></span>
                </a>
            </div>
            <% }%>
            <!------ END Start Dynamically loading END----------->
        </div>
        <div class="checkbox">
            <label><input id="hasTest" type="checkbox" value="">Test</label>
        </div>
        <span class="input-group-btn" style="display: block; margin-top: 20px" title="Submit">
                <button id="submit_btn" class="btn btn-success glyphicon glyphicon-ok" type="button">&nbsp;Submit</button>
                <script>
                    $("#submit_btn").click(function(){
                        alert();
                        var module_name = $("#module_name_field").val();
                        var hasTest =0;
                        if($("#hasTest").is(':checked')){
                            hasTest = 1; //1 = true
                        }

                        var page_to_go = 'UpdateModuleName?' +
                            'userId=<%=userId%>&courseId=<%=courseId%>' +
                            '&moduleId=<%=moduleId%>' +
                            '&module_name='+module_name+
                            '&hasTest='+hasTest;
                        alert(page_to_go);
                        location.href=page_to_go;
                    });
                </script>

                <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 20px;"
                        onclick="location.href='newCourse?courseId=<%=courseId%>&userId=<%=userId%>';">&nbsp;Back</button>
        </span>
    </div>
</div>
</body>
</html>