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
public class Topic {
    @Parent @Index private Key<Module> module;
    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id public Long id;
    // 2 attributes
    @Index private String name;
    @Index private int level; //How we prevent users from skipping topics.

    public Topic(){
        this.name = "default";
        this.level = 0;
    }

    public Topic(String name, int level, Module m){
        this();
        module = Key.create(Module.class, m.id);
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

    public Key<Module> getTheParentModule() {
        return module;
    }

    public void setTheParentModule(Key<Module> module) {
        this.module = module;
    }
}
