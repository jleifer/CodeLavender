package cLPackage.dataStore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

/**
 * Original created by Yifang Cao on 2/11/2017.
 * Edited by Konstantinos Pagonis on 4/6/2017
 */
@Entity
public class TopicContent {
    @Parent @Index private Key<Topic> topic;
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 2 attributes
    @Index private String name;
    @Index private String text;
    @Index private String embed;
    @Index private int level; //How we prevent users from skipping topics.

    public TopicContent(){
        this.name = "default";
        this.level = 0;
    }

    public TopicContent(String name, int level, Topic t){
        this();
        topic = Key.create(Topic.class, t.id);
        if(name!=null){
            this.name = name;
        }
        if(level >= 0){
            this.level = level;
        }
    }


}
