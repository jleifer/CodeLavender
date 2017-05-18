<%@ page import="cLPackage.dataStore.MultipleChoices" %>
<%@ page import="cLPackage.dataStore.Topic" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: AjaxSurangama
  Date: 4/21/2017
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    ObjectifyService.register(MultipleChoices.class);
    String userId = (String)request.getParameter("userId").toString();
    String courseId = (String)request.getParameter("courseId").toString();
    String moduleId = (String)request.getParameter("moduleId").toString();
    String topicId = (String)request.getParameter("topicId").toString();

    List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).list();
    Topic topic = null;
    for(int i = 0; i<topicList.size();i++){
        if(topicList.get(i).getId()==Long.parseLong(topicId)){
            topic = topicList.get(i);
        }
    }
    Key<Topic> topicKey = Key.create(Topic.class,Long.parseLong(topicId));
    List<MultipleChoices> quizList = ObjectifyService.ofy().load().type(MultipleChoices.class).ancestor(topicKey).list();

    session.setAttribute("quizList",quizList);
    session.setAttribute("userId",userId);
    session.setAttribute("courseId",courseId);
    session.setAttribute("moduleId",moduleId);
    session.setAttribute("topicId",topicId);
    System.out.println("quizList size"+quizList.size());
%>

<!DOCTYPE html>
<html>
<head>
    <meta name="google-signin-client_id" content="960219417263-di4ik7aduhjj4i9ulc5fjfcskjc2puj6.apps.googleusercontent.com">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Javascript -->
    <script src="../../resources/js/jquery-3.1.1.min.js"></script>
    <script src="../../resources/js/bootstrap.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="../../resources/js/googleLogin.js" async defer></script>
    <link rel="stylesheet" href="../../resources/css/bootstrap.css">
    <!-- Start our file -->
    <script src="../../resources/js/googleLogin.js"></script>
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <link rel="stylesheet" href="../../resources/css/viewTopic-style.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
    <script src="../../resources/js/viewTopic.js"></script>
    <title>DevRoot</title>


</head>
<body onload="loadAuth()">

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


<div style="width: 1000px; margin:auto;">
    <div style="width: 1000px;  margin-top: 20px; border:1px solid grey; box-shadow:  1px 1px 14px #888888;">


        <h1 style="margin-left: 50px;"><%=topic.getName()%></h1>
        <div style="display: block;"><div>
        <div style="width: 800px;margin-left: 50px;
			background-color: rgba(230,230,250,0.9);
			font-size: 19px; padding: 10px;box-shadow:  1px 1px 14px #888888;">
            <%=topic.getContent()%>

            <br style="clear: both;"/>
        </div>






        <hr />
        <h1 style="margin-left: 50px;">Topic Quizz</h1>

        <!---------Start  dyanamical Generating---->
        <% for (int i = 0 ; i<quizList.size();i++){%>
        <div style=" width: 800px; margin-left: 60px;
  font: 16px Raleway, sans-serif; margin-left: 50px; margin-bottom: 30px; ">
            <b style="font-size: 18px;"><%=(i+1)+". "%>&nbsp;</b>
        <%=quizList.get(i).getQuestionText()%>
        </div>
        <%  String options[] = quizList.get(i).getOptions();
            for (int k = 0 ; k<options.length;k++){
                if(quizList.get(i).getAnswer()==k+1){
                    %>
        <div id="quiz_<%=i+1%>_<%=k+1%>" onclick="selectOption(<%=i+1%>,<%=k+1%>,1 )" class="mutic"><b ><%=options[k]%></b></div>
        <%
                }else{
                    %>
        <div id="quiz_<%=i+1%>_<%=k+1%>" onclick="selectOption(<%=i+1%>,<%=k+1%>,0 )" class="mutic"><b ><%=options[k]%></b></div>
        <%
                }
        %><% } %>
        <% } %>
        <script>
            function correct(num){
                if(num==1){
                    alert("correct");
                }else{
                    alert("worng");
                }
            }

        </script>
        <!---------------ENd Generating ----------->

            <!--prepare back btn url-->
            <c:url var="backUrl" value="/viewCourse">
                <c:param name="userId" value="${userId}"/>
                <c:param name="courseId" value="${courseId}"/>
                <c:param name="curUserId" value="${curUserId}"/>
            </c:url>

    </div>
    <br><br>
    <span class="input-group-btn" style="display: block; margin-top: 20px;margin:auto;width:150px;" title="Submit">
        <button class="btn btn-success glyphicon glyphicon-ok" type="button"
                id ="submit_quiz_btn">&nbsp;Submit Quiz</button>

        <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 320px;"
                id ="back_btn" onclick="location.href='${backUrl}'">&nbsp;Back</button>
    </span>
    <br><br>
</div>

    </div>
</div>

<!-- Quiz Answer Response -->
<div class="quizResponse" id="quizResponse">
    <h1 >Quiz Results</h1>
    <h2 id="quiz_proficiency">Proficiency:</h2>
    <br><br>
    <c:forEach var="i" begin="1" end="${fn:length(quizList)}">
        <h2 id="quizResponse${i}" style="background:lawngreen;">Quiz 1:</h2>
    </c:forEach>
    <br><br>
    <span class="input-group-btn" style="display: block; margin-top: 20px;margin:auto;width:90px;" title="Submit">
        <button class="btn btn-success glyphicon glyphicon-ok" type="button"
                id ="ok_btn">&nbsp;OK</button>
    </span>
</div>

<!--Hidden Form for recording and submitting quiz answers-->

<form id="takeQuizForm" action="TakeTopicQuizServlet" method="GET">
    <input type="hidden" name="quizNum"  id="quizNum"  value="<c:out value="${fn:length(quizList)}"/>">
    <input type="hidden" name="userId" value="<c:out value="${userId}" />">
    <input type="hidden" name="curUserId" value="${curUserId}"/>
    <input type="hidden" name="courseId" value="<c:out value="${courseId}" />">
    <input type="hidden" name="moduleId" value="<c:out value="${moduleId}" />">
    <input type="hidden" name="topicId" value="<c:out value="${topicId}" />">
    <input type="hidden" id="topicProficiency" name="topicProficiency" value="0">
    <c:forEach var="i" begin="1" end="${fn:length(quizList)}">
        <input type="hidden" id="quizAnswer${i}" name="quizAnswer${i}" value="-1">
        <!-- val=-1 not answered yet -->
    </c:forEach>

</form>
</body>
</html>