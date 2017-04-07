<%--
  Created by IntelliJ IDEA.
  User: Spartanrme
  Date: 4/6/2017
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bootstrap Login Form Template</title>

  <!-- CSS -->
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="../../resources/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="../../resources/css/form-elements.css">
  <link rel="stylesheet" href="../../resources/css/style.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Favicon and touch icons -->
  <link rel="shortcut icon" href="../../resources/ico/favicon.png">
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../../resources/ico/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../../resources/ico/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../../resources/ico/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="../../resources/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

<!-- Top content -->
<div class="top-content">

  <div class="inner-bg">
    <section id="login">
      <div class="container">
        <div class="row">
          <div class="col-sm-8 col-sm-offset-2 text">
            <img src="../../resources/img/dev.png" alt="*Logo*">
            <h1><strong>dev</strong>Root</h1>
            <div class="description">
              <p>
                The place to build a foundation in mathematical logic.
              </p>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3 social-login">
            <!--<h3>...or login with:</h3>-->
            <div class="social-login-buttons">
              <!--<a class="btn btn-link-1 btn-link-1-facebook" href="#">-->
              <!--<i class="fa fa-facebook"></i> Facebook-->
              <!--</a>-->
              <!--<a class="btn btn-link-1 btn-link-1-twitter" href="#">-->
              <!--<i class="fa fa-twitter"></i> Twitter-->
              <!--</a>-->
              <%--<a class="btn btn-link-1 btn-link-1-google-plus" >--%>
                <%--<i class="fa fa-google-plus" ></i> Login With Google--%>
              <%--</a>--%>
              <center><div class="g-signin2" data-onsuccess="onSignIn"></div></center>
              <a href="#" onclick="signOut();">Sign out</a>
              <br>
              <br>
              <a href="#about" class="page-scroll btn btn-primary">About Us</a>
            </div>
            <!-- Services Section -->
          </div>
        </div>
      </div>
    </section>
  </div>
  <!-- About Section -->
  <section id="about">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading">About Us</h2>
          <!--<h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>-->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <ul class="timeline">
            <li>
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/1.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>Goal</h4>
                  <!--<h4 class="subheading">Our Humble Beginnings</h4>-->
                </div>
                <div class="timeline-body">
                  <p class="text-muted">
                    We seek to provide an environment where users can learn mathematical logic in a creative and collaborative manner.
                  </p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/2.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>Process</h4>
                  <!--<h4 class="subheading">An Agency is Born</h4>-->
                </div>
                <div class="timeline-body">
                  <p class="text-muted">
                    Our site provides this learning environment by allowing users to construct their own mathematical logic courses.
                    These courses can be customized with multiple modules and topics that contain the material that other users can learn from.
                    We also provide the ability to exercise your familiarity with the content by allowing for the creation of quizzes containing mathematical logic questions in each module and topic within a course.
                  </p>
                </div>
              </div>
            </li>
            <li>
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/3.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>View Your Progress</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">
                    Each course that you navigate to allows you to see your familiarity with its learning material by displaying your highest exercise score next to the respeective topic or module in a course.
                    After you log in, you can click on your profile page to get a quick view of the courses that you have started, and jump back into the learning by clicking on one.
                    Any courses that you create can also be accessed from your profile page to improve them.
                  </p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/4.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>Become an Instructor</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">
                    If you are an instructor, let us know.  After verifying that you are an instructor, we can specify that your courses have been created by an actual instructor for the material.
                    This will allow your content to be clearly visible by students looking to learn from an instructor.
                  </p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <h4>Join us in creating a place to foster the learning of mathematical logic today!</h4>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>

</div>
<!-- Javascript -->
<script src="../../resources/js/jquery-3.1.1.min.js"></script>
<script src="../../resources/js/bootstrap.js"></script>
<script src="../../resources/js/jquery.backstretch.min.js"></script>
<script src="../../resources/js/scripts.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="../../resources/js/todo.js" async defer></script>
<meta name="google-signin-client_id" content="1027240453637-n7gq0t7hs7sq0nu30p4keu797ui3rhcm.apps.googleusercontent.com">

<!--[if lt IE 10]>
<!--<script src="js/placeholder.js"></script>-->
<![endif]-->

</body>

</html>
