package cLPackage.dataStore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.awt.font.ShapeGraphicAttribute;
import java.io.Serializable;

/**
 * Created by Yifang Cao on 4/20/2017.
 */
@Entity
public class MultipleChoices implements Serializable  {
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Parent
    @Index private Key<Topic> topic;

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

    public Long getParentTopicID() { return topic.getId(); }
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
    public Long getID() { return id; }
}
