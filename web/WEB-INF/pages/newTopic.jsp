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

        <!---------!Topic Name ---------->
        <div class="input-group input-group-lg col-xs-5">
            <span class="input-group-addon" id="sizing-addon1">Topic Name</span>
            <input type="text" class="form-control" placeholder="Topic One" aria-describedby="sizing-addon1" disabled>
        </div>

        <br>
        <hr>
        <!---------Add Topic Text ---------->
        <div class="input_fields_wrap" style="width: 1000px;margin:auto; background-color: rgba(230,230,250,0.9);
			font-size: 19px; padding: 10px;box-shadow:  1px 1px 14px #888888; ">
            <label class="control-label">Topic Content</label>
            <textarea rows="10" cols="100" name="topic_text" style="display: block;">
                Add Topic Content Here...
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
                <span id="quiz_total_num" >Total: 0</span>
                <br clear="both;">
            </div>
        </div>

        <!---------END Add Topic Quiz Control END------------>
        <div class="bot_buffer_div">
            <form action="TopicQuiz" method="get"></form>
        </div>
    </div>
</div>
</body>
</html>