/**
 * Created by Jonathan on 5/10/2017.
 */

$("#submit_btn").click(function() {
    alert("retrieving data from page to update course");
    var updatedCourse = {
        name : $("#course_name_field").val(),
        description : $("#course_description").val(),
        imgURL : $("#img_url").val(),
        isPublic : 0
    };
    if($("#isPublic").is(':checked')) {
        updatedCourse.isPublic = 1;
    }
    alert("Redirecting to submit edits to server-side")
    location.href="/updateCourse?courseId=${courseToEdit.id}"
})