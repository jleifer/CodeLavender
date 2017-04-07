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
    @Parent @Index private Key<User> user;
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 4 attributes
    @Index private String name;
    @Index private String owner;
    @Index private int endorsedByUsers;
    @Index private int endorsedByInstructors;

    public Course(){
        this.name = "default";
        this.owner = "Anonymous";
        this.endorsedByUsers = 0;
        this.endorsedByInstructors = 0;
    }

    public Course(String name, String owner, int endorsedByUsers,
                  int endorsedByInstructors, User u){
        this();
        user = Key.create(User.class, u.id);
        if(name!=null){
            this.name = name;
        }
        if(owner!=null){
            this.owner = owner;
        }
        if(endorsedByUsers >= 0){
            this.endorsedByUsers = endorsedByUsers;
        }
        if(endorsedByInstructors >= 0){
            this.endorsedByInstructors = endorsedByInstructors;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getEndorsedByUsers() {
        return endorsedByUsers;
    }

    public void setEndorsedByUsers(int endorsedByUsers) {
        this.endorsedByUsers = endorsedByUsers;
    }

    public int getEndorsedByInstructors() {
        return endorsedByInstructors;
    }

    public void setEndorsedByInstructors(int endorsedByInstructors) {
        this.endorsedByInstructors = endorsedByInstructors;
    }

    public Key<User> getTheParentUser() {
        return user;
    }

    public void setTheParentUser(Key<User> user) {
        this.user = user;
    }
}
