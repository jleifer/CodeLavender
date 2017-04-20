package cLPackage.dataStore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by Yifang Cao on 4/20/2017.
 */
@Entity
public class MultipleChoices {
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id
    public Long id;

    //Attributes
    @Index private int optionNumber;
    @Index private String questionText;
    @Index private String options[];
    @Index private int answer;

    public MultipleChoices(){
        optionNumber = 2; //True or False question by default.
        questionText = "no question text input";
        options = new String[optionNumber];
        options[0] = "True";
        options[1]="False";
    }
    public MultipleChoices(String questionText,int optionNumber, int answer, String options[]){
        this.optionNumber = optionNumber;
        this.answer = answer;
        this.questionText = questionText;
        if(options!=null && optionNumber>2){
            for (int i = 0; i<optionNumber;i++){
                this.options[i]= new String(options[i]);
            }
        }
    }
}