/**
 * Created by Jonathan on 5/10/2017.
 */
/*$("#submit_btn").click(function(){
    alert();
    var course_name = $("#course_name_field").val();
    var course_description = $("#course_description").val();
    var img_url = $("#img_url").val();
    var isPubshed =0;
    if($("#isPubshed").is(':checked')){
        isPubshed = 1; //1 = true
    }

    var page_to_go = 'UpdateModuleServlet?' +
        'courseId=${courseToEdit.}<%=courseId%>' +
        '&course_name='+course_name+
        '&course_description='+course_description+
        '&img_url='+img_url+
        '&isPubshed='+isPubshed;
    alert(page_to_go);
    location.href=page_to_go;
});*/

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