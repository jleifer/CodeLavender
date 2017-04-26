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
    String str = request.getParameter("searchStr");
    ObjectifyService.register(Course.class);
    String email = request.getParameter("email");
    User user = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0);
    List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
    Long userId = user.getId();

%>

<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
</head>
<body>
<br style="clear: both;">
<% for (int i  = 0; i<courseList.size();i++){%>
<div class="main-page-single-rec" onclick="location.href='viewCourse?userId=<%=userId%>&courseId=<%=courseList.get(i).getId()%>';">
    <% if(str.toLowerCase().equals(courseList.get(i).getName().toLowerCase()) ){%>
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
    <%}
    else%>
    <p> No results to display</p>
<%}%>
</body>
</html>
