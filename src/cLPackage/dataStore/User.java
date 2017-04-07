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
    //Primary Key
    @Id public Long id;
    // 3 attributes
    @Index private String name;
    @Index private String owner;
    @Index private int isInstructor;
    @Index private Date created;
    @Index private int[] endorsed;


    public User(){
        this.name = "default";
        this.owner = "Anonymous";
        this.isInstructor = 0;
        this.created = new Date();
        this.endorsed = new int[100];
    }

    public User(String name, String owner, int isInstructor, Date created, int[] endorsed){
        this();
        if(name != null){
            this.name = name;
        }
        if(owner != null){
            this.name = owner;
        }
        if(isInstructor >= 0){
            this.isInstructor = isInstructor;
        }
        if(created != null){
            this.created = created;
        }
        if(endorsed != null){
            this.endorsed = endorsed;
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

    public int[] getEndorsed(){
        return endorsed;
    }

    public void setEndorsed(int[] endorsed){
        this.endorsed = endorsed;
    }
}
