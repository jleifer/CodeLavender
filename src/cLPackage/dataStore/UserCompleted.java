/* A user completed keeps track of what courses a user has completed. It also tracks the progress of a course a user
 * is taking by tracking the quizzes they have taken within the course.
 */
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
    @Parent @Index private Key<User> user; // What user this user completed belongs to.
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;

    // 7 attributes
    @Index private long courseID; // The course that contains the module and topics.
    @Index private long moduleID; // The module belonging to the course.
    @Index private long topicID; // The topic belonging to the course.
    @Index private int moduleLevel; // The amount of module quizzes a user has completed within the course.
    @Index private int topicProficiencyScore; // The amount of topic quizzes a user has completed within the course.
    @Index private int completed; /* 0 - false, 1 - true; If a user completed a course - true when all quizzes
    * have been completed. */
    @Index private int rating; // An int with value 0 - 5; Denotes the rating a user gave the course.
    // If the rating is -1 then the user hasn't rated the course yet.

    // Default constructor
    public UserCompleted(){
        this.courseID = 0L;
        this.moduleID = 0L;
        this.topicID = 0L;
        this.moduleLevel = 0;
        this.topicProficiencyScore = 0;
        this.completed = 0;
        this.rating = 0;
    }

    // Constructor
    public UserCompleted(long courseID, long moduleID, long topicID, int moduleLevel,
                         int topicProficiencyScore, int completed, int rating, User u){
        this();
        user = Key.create(User.class, u.id);
        if(courseID >= 0L){
            this.courseID = courseID;
        }
        if(moduleID >= 0L){
            this.moduleID = moduleID;
        }
        if(topicID >= 0L){
            this.topicID = topicID;
        }
        if(moduleLevel >= 0){
            this.moduleLevel = moduleLevel;
        }
        if(topicProficiencyScore >= 0){
            this.topicProficiencyScore = topicProficiencyScore;
        }
        if(completed >= 0){
            this.completed = completed;
        }
        if(rating >= -1 && rating <= 5){
            this.rating = rating;
        }
    }

    // Getters and setters
    public Key<User> getParentUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setCourseID(long courseID) { this.courseID = courseID; }

    public long getCourseID() { return courseID; }

    public long getModuleID() {
        return moduleID;
    }

    public long getTopicID() {
        return topicID;
    }

    public int getModuleLevel() {
        return moduleLevel;
    }

    public int getTopicProficiencyScore() {
        return topicProficiencyScore;
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

    public void setTopicProficiencyScore(int topicProficiencyScore) {
        this.topicProficiencyScore = topicProficiencyScore;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setRating(int rating) { this.rating = rating; }

    public int getRating() { return rating; }
}
