/* A module is is a collection of related topics (and their associated quizzes). A module can have a quiz associated
 * with it.
 * Each module has at least one topic.
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
public class Module {
    @Parent @Index private Key<Course> course; // What course this module belongs to.
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 2 attributes
    @Index private String name; // The name of the module.
    @Index private int hasTest; // 0 - false, 1 - true. If the module has a test.

    // Default constructor
    public Module(){
        this.name = "default";
        this.hasTest = 0;
    }

    // Constructor
    public Module(String name, Course c){
        this();
        course = Key.create(Course.class, c.id);
        if(name!=null){
            this.name = name;
        }
        if(hasTest >= 0){
            this.hasTest = hasTest;
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

    public int getHasTest() {
        return hasTest;
    }

    public void setHasTest(int hasTest) {
        this.hasTest = hasTest;
    }

    public Key<Course> getTheParentCourse() {
        return course;
    }

    public void setTheParentCourse(Key<Course> course) {
        this.course = course;
    }
}
