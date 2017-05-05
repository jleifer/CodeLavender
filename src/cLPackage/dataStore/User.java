/* A user is a person that registers their account with the website using their Google email. */
package cLPackage.dataStore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Original created by Yifang Cao on 2/11/2017.
 * Edited by Konstantinos Pagonis on 4/6/2017
 */
@Entity
public class User {
    // Primary Key
    @Id public Long id;
    // 6 attributes

    @Index private String firstName; // A user's first name.
    @Index private String lastName; // A user's last name.
    @Index private int isInstructor; // If the user is an instructor. For now this is simply a label for some users.
    // An instructor is requested by a user through email.
    @Index private Date created; // The date that the user was registered on.
    @Index private String email; // The user's email address. This is automatically obtained from the login page.
    @Index private int[] endorsed; // What courses a user has endorsed.

    // Default constructor
    public User(){
        this.firstName = "default";
        this.lastName = "last";
        this.isInstructor = 0;
        this.created = new Date();
        this.email = "no email";
        this.endorsed = new int[100]; // Only 100 endorses.
    }

    // Constructor
    public User(String firstName, String lastName, int isInstructor, String email, Date created, int[] endorsed){
        this();
        if(firstName != null){
            this.firstName = firstName;
        }
        if(lastName != null){
            this.lastName = lastName;
        }
        if(isInstructor >= 0){
            this.isInstructor = isInstructor;
        }
        if(email!=null){
            this.email = email;
        }
        if(created != null){
            this.created = created;
        }
        if(endorsed != null){
            this.endorsed = endorsed;
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){ return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getIsInstructor() {
        return isInstructor;
    }

    public void setIsInstructor(int isInstructor) {
        this.isInstructor = isInstructor;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmail(){return this.email;}

    public void setEmail(String email){this.email=email;}

    public int[] getEndorsed(){
        return endorsed;
    }

    public void setEndorsed(int[] endorsed){
        this.endorsed = endorsed;
    }
}
