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
    @Parent @Index private Key<Course> course;
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 2 attributes
    @Index private String name;
    @Index private int level; //How we prevent users from skipping modules.

    public Module(){
        this.name = "default";
        this.level = 0;
    }

    public Module(String name, int level, Course c){
        this();
        course = Key.create(Course.class, c.id);
        if(name!=null){
            this.name = name;
        }
        if(level >= 0){
            this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Key<Course> getTheParentCourse() {
        return course;
    }

    public void setTheParentCourse(Key<Course> course) {
        this.course = course;
    }
}
