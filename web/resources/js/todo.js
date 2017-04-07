/**
 * Created by Randhawa on 2/7/17.
 */
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    window.open("hello.jsp","_self",false);
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
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
    window.open("/index.jsp","_self",false);
}
