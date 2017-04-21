/**
 * Created by Yifang Cao on 4/17/17.
 */
var cur_quiz_type = "TorF";
var topic_quiz_num = 0;
var quizes = [];
$(document).ready(function() {

    $(".text_field_option").on("change",function () {
        changeMultiChoice($(this));
    });

    $('#cur_quiz_type').on('change', function() {
        cur_quiz_type = this.value;
        $("#hidden_quiz_type").val(cur_quiz_type);
        console.log( cur_quiz_type );
        /*
        multi_4
        multi_5
        multi_6
        TorF
        */
    });

    $('#add_quiz_btn').click(function(){

        $("#hidden_form_for_creation").submit();

        topic_quiz_num++; //increment quiz numer
        //make a quiz
        var $quiz_question_div = $("<div>",{"class":"quiz_question"});
        var $quiz_question_label = $("<label>",{"class":"quiz_label"});
        var label_text = "Question Type:";
        var $delete_btn = $("<span>",{"style":"color:red;cursor:pointer;margin-left:10px;"});
        var $quiz_content_textarea = $("<textarea>",{"rows":3,"cols":70,"style":"width:600px;display:block; margin:auto;"});
        var $quiz_ans_label = $("<label>",{"class":"quiz_label"});
        var $ans_select = $("<select>");
        //END

        //assemble everything
        $delete_btn.html("Delete");
        $quiz_ans_label.html("Answer: ");
        $delete_btn.click(function(){
            var r = confirm("Delete current question?");
            if(r==true)
                removeQuiz(topic_quiz_num);
        });
        if(cur_quiz_type =="TorF"){
            label_text +=" (True or False)";

        }else {
            var multi_type = cur_quiz_type.split("_")[1];
            label_text +=" (Multiple Choices("+multi_type+"))";
        }
        $quiz_question_label.html(label_text);
        $quiz_question_div.append($quiz_question_label);
        $quiz_question_div.append($delete_btn);
        $quiz_question_div.append($("<br>"));
        $quiz_question_div.append($quiz_content_textarea);
        if(cur_quiz_type =="TorF"){
            var $true_div = $("<div>", {"class":"mutic"});
            var $false_div = $("<div>", {"class":"mutic"});
            $true_div.html("True");
            $false_div.html("False");
            $quiz_question_div.append($true_div);
            $quiz_question_div.append($false_div);
            var $true_option =$("<option>");
            var $false_option =$("<option>");
            $true_option.html("1:True");
            $false_option.html("2:False");
            $ans_select.append($true_option);
            $ans_select.append($false_option);
        }else{
            var multi_type = cur_quiz_type.split("_")[1];
            for(var i  = 0 ; i<multi_type;i++){
                var $multi_choice_div = $("<div>", {"class":"mutic"});
                var $multi_choice_input = $("<input>",
                    {"type":"text", "style":"height:30px;" +
                    "width:300px;" +
                    "background-color:rgba(151,215,233,0.5);" +
                    "margin-top:5px;" +
                    "text-align: center; "});
                $multi_choice_input.val("Choice "+(i+1));
                $multi_choice_div.append($multi_choice_input);
                $quiz_question_div.append($multi_choice_div);

                var $multi_choice_option =$("<option>");
                $multi_choice_option.html((i+1)+". Choice "+(i+1));
                $ans_select.append($multi_choice_option);
                $multi_choice_input.on("change",function () {
                    changeMultiChoice($(this));
                });
            }
        }
        $quiz_question_div.append($quiz_ans_label);
        $quiz_question_div.append($ans_select);
        $("#topic_quiz_content").append($quiz_question_div);
        //END make a quiz END


        //make the quiz object
        quizes.push();
        console.log("quiz ans: " +quizes );
        //$("#quiz_total_num").html("Total: "+topic_quiz_num);
    });

});

function removeQuiz(whichOne){
    $(".quiz_question:nth-child("+ whichOne+" )").remove();
    topic_quiz_num--;
   // $("#quiz_total_num").html("Total: "+topic_quiz_num);
}
function changeMultiChoice(input){
    var cur_val = input.val();
    var parent = input.parent();
    var cur_index = parent.index()-4;
    parent = parent.parent();
    var select = parent.find("select");
    var option  = select.children()[cur_index];
    $(option).html((cur_index+1)+". "+cur_val)
}