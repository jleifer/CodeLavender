<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 2/7/2017
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
  from https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<h2>${message}</h2>
Click <a onclick="location.href='/'" href="/">${returnlink}</a> to return to the initial page.
</body>
</html>