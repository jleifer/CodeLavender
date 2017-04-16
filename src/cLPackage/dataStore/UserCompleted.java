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
public class UserCompleted {
    /* IDs which user this belongs to. */
    @Parent @Index private Key<User> user;
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;

    // 5 attributes
    @Index private long moduleID;
    @Index private long topicID;
    @Index private int moduleLevel;
    @Index private int topicLevel;
    @Index private int completed;

    //Default constructor
    public UserCompleted(){
        this.moduleID = 0L;
        this.topicID = 0L;
        this.moduleLevel = 0;
        this.topicLevel = 0;
        this.completed = 0;
    }

    //Constructor
    public UserCompleted(long moduleID, long topicID, int moduleLevel,
                  int topicLevel, int completed, User u){
        this();
        user = Key.create(User.class, u.id);
        if(moduleID >= 0L){
            this.moduleID = moduleID;
        }
        if(topicID >= 0L){
            this.topicID = topicID;
        }
        if(moduleLevel >= 0){
            this.moduleLevel = moduleLevel;
        }
        if(topicLevel >= 0){
            this.topicLevel = topicLevel;
        }
        if(completed >= 0){
            this.completed = completed;
        }
    }

    //Getters and setters
    public Key<User> getParentUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public long getModuleID() {
        return moduleID;
    }

    public long getTopicID() {
        return topicID;
    }

    public int getModuleLevel() {
        return moduleLevel;
    }

    public int getTopicLevel() {
        return topicLevel;
    }

    public int getCompleted() {
        return completed;
    }

    public void setParentUser(Key<User> user) {
        this.user = user;
    }

    public void setModuleID(long moduleID) {
        this.moduleID = moduleID;
    }

    public void setTopicID(long topicID) {
        this.topicID = topicID;
    }

    public void setModuleLevel(int moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    public void setTopicLevel(int topicLevel) {
        this.topicLevel = topicLevel;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
