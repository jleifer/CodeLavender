/* A multiple choice is a type of question found on quizzes. They consist of a customizable amount of answers to a
 * customizable question. A quiz can have any number amount of multiple choice questions.
 */
package cLPackage.dataStore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

/**
 * Created by Yifang Cao on 4/20/2017.
 */
@Entity
public class MultipleChoices {

    @Parent
    @Index private Key<Topic> topic; // What topic this multiple choice belongs to.
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id
    public Long id;

    // 4 attributes
    @Index private int optionNumber; // What number question this is.
    @Index private String questionText; // The description of the question being asked.
    @Index private String options[]; // The possible answers to the question. Option 1 = Index 0 and so on.
    @Index private int answer; // Which index is the correct answer.

    // Default constructor
    public MultipleChoices(){
        optionNumber = 2; //True or False question by default.
        questionText = "no question text input";
        options = new String[optionNumber];
        options[0] = "True";
        options[1]="False";
    }

    // Constructor
    public MultipleChoices(String questionText,int optionNumber, int answer, String options[],Topic topic){
        this.topic = Key.create(Topic.class, topic.id);
        this.optionNumber = optionNumber;
        this.answer = answer;
        this.questionText = questionText;
        if(options!=null && optionNumber>2){
            this.options = new String[optionNumber];
            for (int i = 0; i<optionNumber;i++){
                this.options[i]= new String(options[i]);
            }
        }
    }

    // Getters and setters
    public int getOptionNumber(){
        return this.optionNumber;
    }
    public void setOptionNumber(int optionNumber){
        this.optionNumber =optionNumber;
    }
    public String getQuestionText(){
        return this.questionText;
    }
    public void setQuestionText(String questionText){
        this.questionText =questionText;
    }
    public String[] getOptions(){
        return this.options;
    }
    public void setOptions(String[] options){
        this.options =options;
    }
    public int getAnswer(){
        return this.answer;
    }
    public void setAnswer(int answer){
        this.answer =answer;
    }
}
