
/**
 * Created by Yifang Cao on 4/19/2017.
 */

$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID

    var x = 1; //initlal text box count
    /*
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div>' +
                '<span class="btn glyphicon glyphicon-edit" title="edit"></span>'+
                'Name:<input type="text" name="module_name"/>' +
                '<a href="#" class="remove_field">' +
                '<span class="remove_field glyphicon glyphicon-minus-sign"></span></a>' +
                '</div>'); //add input box
        }
        $(".glyphicon-edit").click(function () {
            location.href = "/newTopic";
        });
    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })

    $(".glyphicon-edit").click(function () {
        location.href = "/newTopic";
    });
    */
});