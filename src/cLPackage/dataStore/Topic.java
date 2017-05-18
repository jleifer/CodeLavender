/* A topic is is what contains the information that is to be taught to users taking a course, related to the module
 * they are currently in.
 */
package cLPackage.dataStore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Original created by Yifang Cao on 2/11/2017.
 * Edited by Konstantinos Pagonis on 4/6/2017
 */
@Entity
public class Topic {

   @Index public Key<Module> module; // What module this topic belongs to.
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 3 attributes
    @Index private String name; // Name of the topic.
    @Index private int hasTest; // 0 - false, 1 - true. If the topic has a quiz.
    @Index private String content; // The content of a topic.

    // Default constructor
    public Topic(){
        this.name = "default";
        this.hasTest = 0;
        this.content = "topic";
    }

    // Constructor
    public Topic(String name, int hasTest, String content, Module m){
        this();
        module = Key.create(Module.class, m.id);
        if(name!=null){
            this.name = name;
        }
        if(hasTest >= 0){
            this.hasTest = hasTest;
        }
        if(content != null){
            this.content = content;
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

    public String getContent() { return content; }

    public void setHasContent(String content) { this.content = content; }

    public Key<Module> getTheParentModule() {
        return module;
    }

    public void setTheParentModule(Key<Module> module) {
        this.module = module;
    }
}
