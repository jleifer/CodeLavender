<%@ page import="cLPackage.dataStore.MultipleChoices" %>
<%@ page import="cLPackage.dataStore.Topic" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: AjaxSurangama
  Date: 4/21/2017
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    System.out.println("quizList size"+quizList.size());
%>

<!DOCTYPE html>
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
    <link rel="stylesheet" href="../../resources/css/hello-style.css">
    <link rel="stylesheet" href="../../resources/css/profile.css">
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>
    <title>DevRoot</title>

    <style type="text/css">
        .prevnext{
            width: 150px;
            height: 25px;
            border:1px solid grey;
            background-color: rgb(190,190,210);
            text-align: center;
            position: relative;
            top: 35px;
        }
        .mutic{
            width: 400px;
            height: 40px;
            border-radius: 10px;
            margin: auto;
            margin-top: 3px;
            text-align: center;
            background-color: rgb(101,165,183);
            line-height: 40px;
        }
    </style>
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


<div style="width: 1000px; margin:auto;">
    <div style="width: 1000px;  margin-top: 20px; border:1px solid grey; box-shadow:  1px 1px 14px #888888;">


        <h1 style="margin-left: 50px;"><%=topic.getName()%></h1>

        <div style="width: 800px;margin-left: 50px;
			background-color: rgba(230,230,250,0.9);
			font-size: 19px; padding: 10px;box-shadow:  1px 1px 14px #888888;">
            <%=topic.getContent()%>

            <!--
            <div class="prevnext" style="float: left;">
                Previous Topic
            </div>
            <div class="prevnext" style="margin-left: 650px;">
                Next Topic
            </div>
            -->
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
        %>
        <div class="mutic"><b style="font-size: 20px;"><%=options[k]%></b></div>
        <% } %>
        <% } %>
        <!---------------ENd Generating ----------->

        <!--
        <div style=" width: 800px; margin-left: 60px; margin-top: 50px;
  font: 16px Raleway, sans-serif; margin-left: 50px; margin-bottom: 30px; "><b style="font-size: 18px;">(50%)2.&nbsp;</b>
            Write a Java Program that prints <b>Hello, World!</b> to the terminal window.. </div>

        <textarea style="width:600px; height: 120px; margin-left:60px; color: blue; "autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">public class HelloWorld {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
    }

}
</textarea>


        <div style=" width: 800px; margin-left: 60px; margin-top: 50px;
  font: 16px Raleway, sans-serif; margin-left: 50px; margin-bottom: 30px; "><b style="font-size: 18px;">(30%)3.&nbsp;</b>Complete the following Java snipet with <b>boolean operators </b>, and make the program prints <b>Apple</b> to the terminal. </div>
        <div style="width: 800px; margin-left: 60px; margin-top: 50px;
  font: 18px Raleway, sans-serif; margin-left: 50px; margin-bottom: 30px; ">
  <pre>
public class HelloWorld {

    public static void main(String[] args) {
    	boolean animal = false;
    	boolean notEdible = false;
    	boolean bnum = (3 - 16 > 0);
    	boolean t = true;
    	if( !animal <input style="width: 30px;color: blue;" maxlength="2" type="text" name=""> notEdible || (bnum <input style="color: blue; width: 30px;" maxlength="2" type="text" name="">  t)){
        System.out.println("Apple");
    	}
    }
}
  </pre>

            <div style="margin:auto;width: 150px;
			height: 25px;
			border:1px solid grey;
			background-color: rgb(190,190,210); text-align: center;
			line-height: 25px;">
                <b>Submit</b>
            </div>
        </div>
    -->

    </div>
    <br><br>



</div>

</body>
</html>