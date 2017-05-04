/**
 * Created by Randhawa on 4/17/17.
 */
$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID

    var x = 1; //initlal text box count
    /*
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){
            //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div>' +
                '<span class="btn glyphicon glyphicon-edit" title="edit"></span>'+
                'Name:<input type="text" name="course_name">'+
                'Description:<input type="text" name="course_description">'+
                'Cover Image URL:<input type="text" name="course_img_url">'+
                '<a href="#" class="remove_field">' +
                '<span class="remove_field glyphicon glyphicon-minus-sign"></span></a>' +
                '</div>');
        }
        $(".glyphicon-edit").click(function () {
            location.href = "/newModule";
        });
    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })

    $(".glyphicon-edit").click(function () {
        location.href = "/newModule";
    });
     */
});
