package cLPackage.dataStore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by YifangCao on 5/15/2017.
 */

@Entity
public class UserRating {

    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id
    public Long id;

    // 3 attributes
    @Index
    public long UserId;
    @Index
    public long courseId;
    @Index
    public int rating;

    public UserRating(){
        this.UserId = 0l;
        this.courseId=0l;
        this.rating=0;
    }

    public UserRating(long userId,long courseId, int rating){
        this();
        this.UserId = userId;
        this.courseId=courseId;
        this.rating = rating;
    }
}
