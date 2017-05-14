/**
 * Created by Yifang Cao on 5/14/2017.
 */

var totalScore = 0;
var proficiencyScore = 0; //percentage
var answer_array =[];

$(document).ready(function(){
    var quizNum = $("#quizNum").val();
    //init answer_array
    for(var i = 0; i<quizNum;i++){
        answer_array.push(false);
    }

    $("#ok_btn").click(function(){
        $("#quizResponse").hide();
        $("#takeQuizForm").submit();
    });
    $("#submit_quiz_btn").click(function () {
        //check whether all quizzes are complete
        for(var i =1; i<=quizNum;i++){
            // if one of quizzes is incomplete -1;
            if($("#quizAnswer"+i).val()<0){
                alert("Quiz "+i+" is incomplete.")
                return;
            }
        }
        //calculate and update proficiency score
        totalScore = 0; //reset total
        for(var i =1; i<=quizNum;i++){
            if(answer_array[i]==true)   totalScore++;
        }
        proficiencyScore = totalScore/(quizNum)*100;
        proficiencyScore = Math.round(proficiencyScore);
        $("#topicProficiency").val(proficiencyScore);
        $("#quiz_proficiency").html("Proficiency: "+proficiencyScore+"%");
        $("#quizResponse").show();
        alert($("#topicProficiency").val());
    });
    //alert(quizNum);
});

function selectOption(i,k,ans) {
    //update each quiz's result
    if(ans == 1){
        answer_array[i]=true;
        $("#quizResponse"+i).css("background-color","lawngreen");
        $("#quizResponse"+i).html("Quiz "+i+":Correct");
    }else{
        answer_array[i]=false;
        $("#quizResponse"+i).css("background-color","red");
        $("#quizResponse"+i).html("Quiz "+i+":Incorrect")
    }
    for(var index = 1 ; index<=6 ;index++){
        $("#quiz_"+i+"_"+index).css("background-color","rgb(101,165,183)")
        //alert($("#quiz_"+i+"_"+index).css()+" "+index);
    }
    var $ele = $("#quiz_"+i+"_"+k);
    $ele.css("background-color","green");

    $("#quizAnswer"+i).val(k);
}