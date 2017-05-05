package cLPackage.dataStore;

import com.googlecode.objectify.ObjectifyService;
import java.util.Date;
import java.util.List;

/**
 * Created by Jonathan on 5/4/2017.
 *
 * This class will serve as the main method for interacting with the datastore
 * that will be used to load and save the data for this web application.
 */
public class DataManager {
    /* Singleton Design pattern for accessing the datastore */
    private static DataManager dm = new DataManager();

    private DataManager() {
        /* Register the necessary data classes in the Google DataStore */
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
    }

    public static DataManager getDataManager(){
        return dm;
    }

    /**
     * Retrieve datastore ID for user with matching email address
     *
     * @param email String containing the unique email for the user to find
     *              in the datastore
     * @return Long This returns the userId of the user whose email matches,
     * or -1 otherwise
     */
    public User getUserWithEmail(String email) {
        User user;

        // First check if the user has already exists
        boolean newUser = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().isEmpty();
        if(newUser) {
            // Indicate that user is new
            user = null;
        }
        else{
            user = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0);
        }

        return user;
    }

    /**
     * Creates a new user entity in the datastore with the given
     * first name, last name, and email address.
     *
     * @param firstName First Name of the new user entity to create
     * @param lastName Last Name of the new user entity to create
     * @param email Email Address of the new user entity to create
     */
    public void createUser(String firstName, String lastName, String email) {
        /* Initialize needed values for user creation */
        Date date = new Date();
        int isInstructor = 0;

        //create new User Object and load into datastore
        User user = new User(firstName,lastName,isInstructor,email,date,null);
        ObjectifyService.ofy().save().entity(user).now();
    }
}
