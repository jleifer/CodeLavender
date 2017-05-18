/**
 * Created by Randhawa on 2/7/17.
 */
var userId;
var firstName;
var fullName;
var imgSrc;
var email;
var plusUrl = "https://plus.google.com/";
var profile;
var auth2;
function onSignIn(googleUser) {
    profile = googleUser.getBasicProfile();
    //window.open("/lists_all","_self",false);
    userId =  profile.getId(); // Do not send to your backend! Use an ID token instead.
    localStorage.setItem("userUrl",plusUrl.concat(userId));
    firstName =  profile.getGivenName();
    localStorage.setItem("firstName",firstName);
    fullName = profile.getName();
    localStorage.setItem("fullName",fullName);
    imgSrc = profile.getImageUrl();
    localStorage.setItem("imgSrc",imgSrc);
    email =  profile.getEmail(); // This is null if the 'email' scope is not present.
    localStorage.setItem("email",email);
    $("#loginemail").val(email);
    $("#usernamehiddenform").submit();

}
function onLoad() {
    gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
        });
    });
    $("#FirstName").text(localStorage.getItem("firstName"));
    $("#FullName").text(localStorage.getItem("fullName"));
    $("#Email").text(localStorage.getItem("email"));
    $("img").attr("src",localStorage.getItem("imgSrc"));
    $("#ProfileLink").attr("href",localStorage.getItem("userUrl"));


}

function loadAuth(){

    gapi.load('auth2', function() {
        gapi.auth2.init();

    });
    auth2 = gapi.auth2.getAuthInstance();
}


function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    //window.open("/index","_self",false);
    deleteAllCookies();
    location.href ="https://mail.google.com/mail/u/0/?logout&hl=en";
}
function deleteAllCookies(){
    var cookies = document.cookie.split(";");
    for(var i=0; i < cookies.length; i++) {
        var equals = cookies[i].indexOf("=");
        var name = equals > -1 ? cookies[i].substr(0, equals) : cookies[i];
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}
