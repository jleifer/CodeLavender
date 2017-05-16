package cLPackage.dataStore;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by YifangCao on 5/15/2017.
 */

@Entity
public class UserRequest {

    //have to be capitalized L-ong, not long, its value will be auto-generated
    //Primary Key
    @Id
    public Long id;

    // 1 attributes
    @Index
    public long UserId;

    public UserRequest(){
        this.UserId = 0;
    }

    public UserRequest(long userId){
        this.UserId = userId;
    }
}
