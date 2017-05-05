<%@ page import="cLPackage.dataStore.Topic" %>
<%@ page import="com.googlecode.objectify.Key" %>
<%@ page import="com.googlecode.objectify.ObjectifyService" %>
<%@ page import="java.util.List" %>
<%@ page import="cLPackage.dataStore.MultipleChoices" %><%--
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
<html>
<head>
    <meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">

    <link rel="stylesheet" href="../../resources/css/profile.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="../../resources/js/jquery-3.1.1.js"></script>
    <script src="../../resources/js/bootstrap.js"></script>
    <script src="../../resources/js/googleLogIn.js"></script>
    <script src="../../resources/js/newTopicController.js"></script>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad"></script>


    <title>New Topic</title>

    <style type="text/css">
        .topic_quiz_div{
            border:1px solid lightgrey;
            box-shadow:  1px 1px 14px #888888;
            width: 1000px;
            margin:auto;
        }
        #quiz_total_num{
            float: right;
            color:red;
            font-size:18px;
        }
        .quiz_question{
            border:0px solid blue;
            width: 800px;
            margin:auto;
        }
        .quiz_label{
            margin-top:20px;
        }
        .mutic{
            width: 400px;
            height: 40px;
            border-radius: 10px;
            margin: auto;
            margin-top: 3px;
            text-align: center;
            font-size:14px;
            background-color: rgb(101,165,183);
            line-height: 40px;
        }

        .bot_buffer_div{
            width:1px;
            height: 200px;
            margin:auto;
        }
        .text_field_option {
            height: 30px;
            width: 300px;
            background-color: rgba(151, 215, 233, 0.5);
            margin-top: 5px;
            text-align: center;
        }
    </style>
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

        <!---------!Topic Name ---------->
        <div class="input-group input-group-lg col-xs-5">
            <span class="input-group-addon" id="sizing-addon1">Topic Name</span>
            <input id="topic_name" type="text" class="form-control" value="<%=topic.getName()%>" aria-describedby="sizing-addon1" >
        </div>

        <br>
        <hr>
        <!---------Add Topic Text ---------->
        <div class="input_fields_wrap" style="width: 1000px;margin:auto; background-color: rgba(230,230,250,0.9);
			font-size: 19px; padding: 10px;box-shadow:  1px 1px 14px #888888; ">
            <label class="control-label">Topic Content</label>
            <textarea id="topic_description" rows="10" cols="100" name="topic_text" style="display: block;">
                <%=topic.getContent()%>
            </textarea>
        </div>
        <!---------END Add Topic Text END---------->
        <br style="clear: both;">

        <hr>
        <br>

        <!--------- Add Topic Quiz Control------------>

        <div class="topic_quiz_div">
            <div class="input_fields_wrap" style="width: 1000px;margin:auto; ">
                <label class="control-label" style="font-size:20px;">Topic Quiz </label>
            </div>
            <div id="topic_quiz_content">

                <!-------------start Dynamically generating ------------->
                <%
                   for (int i = 0; i<quizList.size();i++){
                       int optionNumber = quizList.get(i).getOptionNumber();
                       String options[] = quizList.get(i).getOptions();
                       int answer = quizList.get(i).getAnswer();
                       String quiz_label_text = "Question Type: (True or False)";
                       if(optionNumber>2){
                           quiz_label_text = "Question Type: (MultipleChoices("+optionNumber+"))";
                       }
                %>
                <div class="quiz_question">
                    <label class="quiz_label"><%=quiz_label_text%></label>
                    <span style="color:red;cursor:pointer;margin-left:10px;"
                          onclick="location.href='DeleteQuizServlet?courseId=<%=courseId%>&userId=<%=userId%>&moduleId=<%=moduleId%>&topicId=<%=topicId%>&quizId=<%=quizList.get(i).id%>';">Delete</span>
                    <br />
                    <textarea rows="3" cols="70" style="width:600px;display:block; margin:auto;"><%=quizList.get(i).getQuestionText()%></textarea>
                    <% for(int k = 0 ; k<options.length; k++){ %>
                    <div class="mutic">
                        <input type="text" class="text_field_option" value="<%=options[k]%>">

                    </div>
                    <% } %>
                    <label class="quiz_label">Answer: </label>
                    <select>
                        <% for(int k = 0 ; k<options.length; k++){
                            String selected = (k==(answer-1))? "selected":"";
                        %>
                        <option <%=selected%>> <%=(k+1)+":"+options[k]%> </option>
                        <% } %>
                    </select>
                </div>
                <%}%>
                <!---------------END Generating END --------------------->

            </div>
            <div style="height: 30px; margin-top:30px;">
                <span class="add_field_button" id="add_quiz_btn"  style="display: block; float: left;" >
                        <button class="btn btn-primary glyphicon glyphicon-plus btn-xs" type="button"></button>
                        New Quiz:&nbsp;
                </span>
                <select id="cur_quiz_type">
                    <option value="TorF" selected >True or False</option>
                    <option value="multi_4" >Multiple Choices(4)</option>
                    <option value="multi_5" >Multiple Choices(5)</option>
                    <option value="multi_6" >Multiple Choices(6)</option>
                </select>
                <span id="quiz_total_num" >Total: <%=quizList.size()%></span>
                <br clear="both;">
            </div>
            <span class="input-group-btn" style="display: block; margin-top: 30px;" title="Submit">
                <button id="submit_edit" class="btn btn-success glyphicon glyphicon-ok" type="button">&nbsp;Submit</button>
                <button class="btn btn-success glyphicon glyphicon-backward" type="button" style="margin-left: 20px;"
                        onclick="location.href='newModule?courseId=<%=courseId%>&userId=<%=userId%>&moduleId=<%=moduleId%>';">&nbsp;Back</button>
            </span>
        </div>
        <!---------END Add Topic Quiz Control END------------>
        <script>
            $("#submit_edit").click(function(){
                var topic_name = $("#topic_name").val();
                var topic_description = $("#topic_description").html();
                var quiz_total_num = $("#quiz_total_num").val();
                var href_to_go = "UpdateTopicQuiz?userId=<%=userId%>&courseId=<%=courseId%>&moduleId=<%=moduleId%>&topicId=<%=topicId%>"+
                    "&topic_name="+topic_name+"&topic_description"+topic_description;
                alert(href_to_go);
                location.href = href_to_go;
            });
        </script>

        <div class="bot_buffer_div" style="clear: both">
            <form action="AddQuizServlet" method="get" id="hidden_form_for_creation">
                <input type="hidden" name="quizType" id="hidden_quiz_type" value="TorF">
                <input type="hidden" name="userId" value="<%=userId%>">
                <input type="hidden" name="courseId" value="<%=courseId%>">
                <input type="hidden" name="moduleId" value="<%=moduleId%>">
                <input type="hidden" name="topicId" value="<%=topicId%>">
            </form>
        </div>
    </div>
</div>
</body>
</html>