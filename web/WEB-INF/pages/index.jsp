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
                The perfect place to learn good programming practices.
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
          <h2 class="section-heading">About</h2>
          <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
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
                  <h4>2009-2011</h4>
                  <h4 class="subheading">Our Humble Beginnings</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/2.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>March 2011</h4>
                  <h4 class="subheading">An Agency is Born</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li>
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/3.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>December 2012</h4>
                  <h4 class="subheading">Transition to Full Service</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <%--<img class="img-circle img-responsive" src="img/about/4.jpg" alt="">--%>
              </div>
              <div class="timeline-panel">
                <div class="timeline-heading">
                  <h4>July 2014</h4>
                  <h4 class="subheading">Phase Two Expansion</h4>
                </div>
                <div class="timeline-body">
                  <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sunt ut voluptatum eius sapiente, totam reiciendis temporibus qui quibusdam, recusandae sit vero unde, sed, incidunt et ea quo dolore laudantium consectetur!</p>
                </div>
              </div>
            </li>
            <li class="timeline-inverted">
              <div class="timeline-image">
                <h4>Be Part
                  <br>Of Our
                  <br>Story!</h4>
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
