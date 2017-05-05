/* A course contains the overall modules and topic content that will be taught to users. It is a a collection of
 * related modules and topics (and their associated quizzes).
 * Each course has at least one module.
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
public class Course {
    @Parent @Index private Key<User> user; // What user this course belongs to.
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 8 attributes
    @Index private String name; // The name of the course.
    @Index private String ownerFirst; // The user's first name.
    @Index private String ownerLast; // The user's last name.
    @Index private int isPublic; // 0 - private, 1 - public; Toggle for making the course visible to the public.
    @Index private int endorsedByUsers; // How many users (not instructors) have endorsed this course.
    @Index private int endorsedByInstructors; // How many instructors have endorsed this course.
    @Index private String description; // A description of the course.
    @Index private String imgURL; // The cover image for the course.

    // Default constructor
    public Course(){
        this.name = "default";
        this.ownerFirst = "Anonymous";
        this.ownerLast = "Last";
        this.isPublic = 0;
        this.endorsedByUsers = 0;
        this.endorsedByInstructors = 0;
        this.description = "No Description";
        this.imgURL = "../../resources/img/rec-img.jpeg";
    }

    // Constructor
    public Course(String name, String ownerFirst, String ownerLast, int isPublic, int endorsedByUsers,
                  int endorsedByInstructors, String description, User u){
        this();
        user = Key.create(User.class, u.id);
        if(name!=null){
            this.name = name;
        }
        if(ownerFirst!=null){
            this.ownerFirst = ownerFirst;
        }
        if(ownerLast!=null){
            this.ownerLast =ownerLast;
        }
        if(isPublic == 0 || isPublic == 1){
            this.isPublic = isPublic;
        }
        if(endorsedByUsers >= 0){
            this.endorsedByUsers = endorsedByUsers;
        }
        if(endorsedByInstructors >= 0){
            this.endorsedByInstructors = endorsedByInstructors;
        }
        if(description!=null){
            this.description=description;
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerFirst() {
        return ownerFirst;
    }

    public void setOwnerFirst(String ownerFirst) {
        this.ownerFirst = ownerFirst;
    }

    public String getOwnerLast() {
        return ownerLast;
    }

    public void setOwnerLast(String ownerLast) {
        this.ownerLast = ownerLast;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getEndorsedByUsers() {
        return endorsedByUsers;
    }

    public void setEndorsedByUsers(int endorsedByUsers) {
        this.endorsedByUsers = endorsedByUsers;
    }

    public String getDescription(){return this.description;}

    public void setDescription(String description){ this.description=description;}

    public int getEndorsedByInstructors() {
        return endorsedByInstructors;
    }

    public void setEndorsedByInstructors(int endorsedByInstructors) {
        this.endorsedByInstructors = endorsedByInstructors;
    }

    public String getImgURL(){return this.imgURL;}

    public void setImgURL(String imgURL){this.imgURL = imgURL;}

    public Key<User> getTheParentUser() {
        return user;
    }

    public void setTheParentUser(Key<User> user) {
        this.user = user;
    }
}
