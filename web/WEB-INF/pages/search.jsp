<%--
  Created by IntelliJ IDEA.
  User: Randhawa
  Date: 4/21/17
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cLPackage.dataStore.Course" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%@ page import="cLPackage.dataStore.User" %>

<%
    String str = request.getParameter("searchStr").trim();
    ObjectifyService.register(Course.class);
    String email = request.getParameter("email");
    User user = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0);
    List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
    Long userId = user.getId();
    int courseFound =0;

%>

<html>
<head>
    <title>Search Results</title>
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
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>


    <title>DevRoot</title>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
</head>
<body>
<br style="clear: both;">
<% for (int i  = 0; i<courseList.size();i++){
        if(str.toLowerCase().equals(courseList.get(i).getName().toLowerCase()) ){
            courseFound++;
%>
        <div class="main-page-single-rec" onclick="location.href='viewCourse?userId=<%=userId%>&courseId=<%=courseList.get(i).getId()%>';">
        <img src="../../resources/img/rec-img2.jpeg" alt="course" style="width:219px;">
        <div class="rec-class-name"><%=courseList.get(i).getName()%></div>
        <div class="rec-creator-name"><%=courseList.get(i).getOwnerFirst()+" "+courseList.get(i).getOwnerLast()%></div>
        <div class="rec-class-intro"><%=courseList.get(i).getDescription()%>
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
        <div class="progress-text">Start Now</div>
        <br style="clear:both;" />
        <br><br><br><br><br><br>
        </div>

    <%
        }
}
if(courseFound==0){
    %>No Results Found<%
}

    %>
</body>
</html>
