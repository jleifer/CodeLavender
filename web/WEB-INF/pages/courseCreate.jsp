<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 4/16/2017
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Create Course</title>
</head>
<body>
<h2> Enter name </h2>
<form:form method = "POST" action = "/addCourse" commandName="course">
    <table>
        <tr>
            <td><form:label path = "name">Name</form:label></td>
            <td><form:input path = "name" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
