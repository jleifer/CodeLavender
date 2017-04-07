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
    <title>Hello World</title>
</head>
<body onload="loadAuth()">
<h2> Hello from this side</h2>
<a href="#" onclick="signOut();">Sign out</a>
<script src="https://apis.google.com/js/platform.js"></script>
<script src="../../resources/js/todo.js"></script>
</body>
</html>