package cLPackage.dataStore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);
        ObjectifyService.register(UserCompleted.class);

    }

    public static DataManager getDataManager(){
        return dm;
    }

    /**
     * Retrieve User datastore entity for user with matching email address.
     *
     * @param email String containing the unique email for the user to find
     *              in the datastore
     * @return User This returns the User entity whose email matches,
     * or null otherwise
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

    public User getUserWithUserId(Long userId) {
        User user = null;

        List<User> userList = ObjectifyService.ofy().load().type(User.class).list();
        for (User u: userList) {
            if (u.getId().equals(userId)) {
                user = u;
                break;
            }
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

    /**
     * Returns a list of all Course entities in the datastore.
     *
     * @return List List of all Course object entities from the datastore.
     */
    public List<Course> getCourseList() {
        return ObjectifyService.ofy().load().type(Course.class).list();
    }

    /**
     * Returns a list of all Course entities in the datastore created by
     * the user with the given user id.
     *
     * @param userId User Id of the User entity in the datastore
     * @return List List of all Course entities from the datastore created
     * by the user with the given user id.
     */
    public List<Course> getCourseListCreatedByUserID(Long userId) {
        /* Create the key to search for the user in the datastore */
        Key<User> userKey = Key.create(User.class, userId);

        List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).ancestor(userKey).list();

        return courseList;
    }

    /**
     * Returns a list of UserCompleted in the datastore created by
     * the user with the given user id.
     *
     * @param userId User Id of the User entity in the datastore
     * @return List List of UserCompleted entities from the datastore created
     * by the user with the given user id.
     */
    public List<UserCompleted> getUserCompletedListCreatedByUserID(Long userId){
        /* Create the key to search for the user in the datastore */
        Key<User> userKey = Key.create(User.class, userId);
        List<UserCompleted> UserCompletedList = ObjectifyService.ofy().load().type(UserCompleted.class).ancestor(userKey).list();
        return UserCompletedList;
    }


    /**
     * Returns list of courses whose names contain the given string,
     * case insensitive.
     *
     * @param courseNameStr String containing part of the name of the
     *              Course entities desired from the datastore
     * @return List List containing Course entities whose names contain
     * the given course name string, case insensitive.
     */
    public List<Course> getCourseListContainingName(String courseNameStr) {
        List<Course> result = new ArrayList<Course>();

        /* Create the pattern to match the course names */
        Pattern namePattern = Pattern.compile(courseNameStr, Pattern.CASE_INSENSITIVE);
        List<Course> courseListToSearch = dm.getCourseList();

        /* Use Regex Pattern to search for courses with matching names */
        for (Course c : courseListToSearch) {
            if (namePattern.matcher(c.getName()).find()){
                result.add(c);
            }
        }

        return result;
    }
}
