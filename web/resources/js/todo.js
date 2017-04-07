/**
 * Created by Randhawa on 2/7/17.
 */
var userId;
var firstName;
var fullName;
var imgSrc;
var email;
var plusUrl = "https://plus.google.com/";
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    fullName = profile.getName();
    imgSrc = profile.getImageUrl();
    email = profile.getEmail();
    localStorage.setItem("userUrl",plusUrl.concat(userId));
    localStorage.setItem("fullName",fullName);
    localStorage.setItem("email",email);
    localStorage.setItem("imgSrc",imgSrc);

    window.open("/hello","_self",false);
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
