/**
 * Created by Randhawa on 2/7/17.
 */
var userId;
var firstName;
var fullName;
var lastName;
var imgSrc;
var email;
var plusUrl = "https://plus.google.com/";
var profile;
var auth2;
function onSignIn(googleUser) {
    profile = googleUser.getBasicProfile();
    userId =  profile.getId(); // Do not send to your backend! Use an ID token instead.
    localStorage.setItem("userUrl",plusUrl.concat(userId));
    firstName =  profile.getGivenName();
    localStorage.setItem("firstName",firstName);
    lastName =  profile.getFamilyName();
    localStorage.setItem("lastName",lastName);
    fullName = profile.getName();
    localStorage.setItem("fullName",fullName);
    imgSrc = profile.getImageUrl();
    localStorage.setItem("imgSrc",imgSrc);
    email =  profile.getEmail(); // This is null if the 'email' scope is not present.
    localStorage.setItem("email",email);
    window.open("/main?firstName="+firstName+"&lastName="+lastName+"&email="+email,"_self",false);
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}

function onLoad() {
    gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
        });
    });
    $("#FirstName").text(localStorage.getItem("firstName"));
    $("#LastName").text(localStorage.getItem("lastName"));
    $("#FullName").text(localStorage.getItem("fullName"));
    $("#Email").text(localStorage.getItem("email"));
    $(".profImg").attr("src",localStorage.getItem("imgSrc"));
    $("#ProfileLink").attr("href",localStorage.getItem("userUrl"));


}

function loadAuth(){
    gapi.load('auth2', function() {
        gapi.auth2.init();

    });
    //var auth2 = gapi.auth2.getAuthInstance();
}

function signOut() {
    gapi.load('auth2', function() {
        gapi.auth2.init();

    });
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    window.open("/index","_self",false);
}
