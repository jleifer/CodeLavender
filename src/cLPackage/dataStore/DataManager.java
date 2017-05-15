package cLPackage.dataStore;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
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
        user = null;

        // First check if the user has already exists
        boolean newUser = false;
        try{
            newUser = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().isEmpty();
        }catch(Exception ex){
            System.out.println("Error in obtaining if user exists.\n" + "Exception: " + ex.getMessage());
        }
        if(newUser) {
            // Indicate that user is new
            user = null;
        }
        else{
            try{
                user = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0);
            }catch(Exception ex){
                System.out.println("Error in obtaining user.\n" + "Exception: " + ex.getMessage());
            }finally{
                if(user == null){
                    /* Let's wait and try again. */
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0);
                }
            }
        }

        return user;
    }

    private User getUserWithUserId(Long userId) {
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

    public Course getCourseWithCourseId(Long courseId) {
        Course course = null;

        List<Course> courseList = dm.getCourseList();
        for (Course c : courseList) {
            if(c.getId().equals(courseId)) {
                course = c;
                break;
            }
        }

        return course;
    }

    public Long getModuleParent(Long moduleId){
        Module module = getModuleWithModuleId(moduleId);
        return module.getTheParentCourse().getId();
    }

    public Module getModuleWithModuleId(Long moduleId) {
        Module module = null;

        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).list();
        for (Module m :moduleList) {
            if(m.getId().equals(moduleId)) {
                module = m;
                break;
            }
        }

        return module;
    }

    public Long getTopicParent(Long topicId){
        Topic topic = getTopicWithTopicId(topicId);
        return topic.getTheParentModule().getId();
    }

    public Topic getTopicWithTopicId(Long topicId) {
        Topic topic = null;

        List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).list();
        for (Topic t: topicList) {
            if(t.getId().equals(topicId)) {
                topic = t;
                break;
            }
        }

        return topic;
    }

    public MultipleChoices getMultipleChoiceFromMultipleChoiceID(Long mcId){
        MultipleChoices mc = null;

        List<MultipleChoices> mcList = ObjectifyService.ofy().load().type(MultipleChoices.class).list();
        for (MultipleChoices m : mcList){
            if(m.getID().equals(mcId)){
                mc = m;
                break;
            }
        }

        return mc;
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

    public Course createCourse(Long userId) {
        /* Retrieve data needed for creation of new course */
        User owningUser = dm.getUserWithUserId(userId);

        /* Create new Course object and save it to the datastore */
        Course newCourse = new Course("New Course", owningUser.getFirstName(), owningUser.getLastName(),
                0, 0, 0, "default description", owningUser);
        ObjectifyService.ofy().save().entity(newCourse).now();
        newCourse = ObjectifyService.ofy().load().entity(newCourse).now();

        System.out.println("New Course Created.");
        System.out.println(newCourse.getId());
        /* Create initial Module and Topic entities within the new Course entity */
        dm.createModule(newCourse.getId(), newCourse);

        return newCourse;
    }

    public void createModule(Long courseId, Course course) {
        /* Retrieve data needed for creation of new module */
        if(course == null) {
            course = dm.getCourseWithCourseId(courseId);
        }

        /* Create new Module object and save it to the datastore */
        if(course == null){
            try {
                Thread.sleep(1000);
                course = dm.getCourseWithCourseId(courseId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Module newModule = new Module("Default Module", course);
        ObjectifyService.ofy().save().entity(newModule).now();

        Long newModuleId = ObjectifyService.ofy().load().entity(newModule).now().id;
        dm.createTopic(newModuleId, newModule);

    }

    public void createTopic(Long moduleId, Module module) {
        /* Retrieve data needed for creation of new topic */
        if(module == null){
            module = dm.getModuleWithModuleId(moduleId);
        }

        /* Create new Topic entity and save it to the datastore */
        if(module == null){
            try {
                Thread.sleep(1000);
                module = dm.getModuleWithModuleId(moduleId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Topic newTopic = new Topic("Default Topic", 0, "No Content", module);
        ObjectifyService.ofy().save().entity(newTopic).now();

        Long newTopicId = ObjectifyService.ofy().load().entity(newTopic).now().id;
        dm.createTopicQuizQuestion(newTopicId, newTopic);
    }

    public void createTopicQuizQuestion(Long topicId, Topic topic) {
        /* Retrieve data needed for creation of new topic */
        if(topic == null){
            topic = dm.getTopicWithTopicId(topicId);
        }

        if(topic == null){
            try {
                Thread.sleep(1000);
                topic = dm.getTopicWithTopicId(topicId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String[] defaultChoices = {"True", "False"};
        MultipleChoices newMC = new MultipleChoices("Default Question Text", 2, 1
                , defaultChoices, topic);
        ObjectifyService.ofy().save().entity(newMC).now();

    }

    public void updateCourse(Long userId, Long courseId, String courseEditName, String courseEditDescription, String courseEditImgURL, int isPublic) {
        /* Retrieve course to update */
        Course courseToUpdate = dm.getCourseWithCourseId(courseId);

        /* Access the datastore to update the entity fields without changing the course id */
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        com.google.appengine.api.datastore.Key userKey = KeyFactory.createKey("User", userId);
        Entity Course = new Entity("Course",courseToUpdate.id,userKey);
        Course.setIndexedProperty("name",courseEditName);
        Course.setIndexedProperty("ownerFirst",courseToUpdate.getOwnerFirst());
        Course.setIndexedProperty("ownerLast",courseToUpdate.getOwnerLast());
        Course.setIndexedProperty("isPublic",isPublic);
        Course.setIndexedProperty("endorsedByUsers",courseToUpdate.getEndorsedByUsers());
        Course.setIndexedProperty("endorsedByInstructors",courseToUpdate.getEndorsedByInstructors());
        Course.setIndexedProperty("description",courseEditDescription);
        Course.setIndexedProperty("imgURL",courseEditImgURL);

        /* Update the entity associated with the current course id */
        ObjectifyService.ofy().delete().entity(courseToUpdate).now();
        datastore.put(Course);
    }

    public Long getCourseOwner(Long courseId){
        Course course = ObjectifyService.ofy().load().type(Course.class).id(courseId).now();
        return course.getTheParentUser().getId();
    }

    /**
     * Returns a list of all Course entities in the datastore.
     *
     * @return List List of all Course object entities from the datastore.
     */
    public List<Course> getCourseList() { return ObjectifyService.ofy().load().type(Course.class).list(); }

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

    public List<Module> getModulesFromCourse(Long courseId) {
        /* Create the key to search for the modules in the datastore */
        Key<Course> courseKey = Key.create(Course.class, courseId);

        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();

        return moduleList;
    }

    public List<Topic> getTopicsFromModule(Long moduleId){
        /* Create the key to search for the topics in the datstore. */
        Key<Module> moduleKey = Key.create(Module.class, moduleId);

        List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).ancestor(moduleKey).list();

        return topicList;
    }

    public Long getMCParent(Long mcId){
        MultipleChoices mc = getMultipleChoiceFromMultipleChoiceID(mcId);
        return mc.getParentTopicID();
    }

    public List<MultipleChoices> getMCFromTopic(Long topicId){
        /* Create the key to search for the MCs in the datastore. */
        Key<Topic> topicKey = Key.create(Topic.class, topicId);

        List<MultipleChoices> mc = ObjectifyService.ofy().load().type(MultipleChoices.class).ancestor(topicKey).list();

        return mc;
    }

    public void deleteCourse(Long courseId){
        /* Now delete the modules associated with this course first. */
        List<Module> moduleList = dm.getModulesFromCourse(courseId);
        for (Module m : moduleList){
            /* Each module will handle deletion of it's own topics. */
            dm.deleteModule(m.getId());
        }
        /* Now delete it. */
        Course courseToDelete = dm.getCourseWithCourseId(courseId);
        //ObjectifyService.ofy().delete().type(Course.class).id(courseId).now();
        ObjectifyService.ofy().delete().entity(courseToDelete).now();
    }

    public void deleteModule(Long moduleId){
        /* Now delete the topics associated with this module first. */
        List<Topic> topicList = dm.getTopicsFromModule(moduleId);
        for(Topic t : topicList){
            /* Each topic will handle deletion of it's own MCs. */
            dm.deleteTopic(t.getId());
        }
        /* Now delete it. */
        //ObjectifyService.ofy().delete().type(Module.class).id(moduleId).now();
        Module moduleToDelete = dm.getModuleWithModuleId(moduleId);
        ObjectifyService.ofy().delete().entity(moduleToDelete).now();
    }

    public void deleteTopic(Long topicId){
        /* Now delete the MCs associated with this topic first. */
        List<MultipleChoices> mc = dm.getMCFromTopic(topicId);
        for(MultipleChoices m : mc){
            /* Each MC will delete itself. */
            dm.deleteMC(m.getID());
        }
        /* Now delete it. */
        //ObjectifyService.ofy().delete().type(Topic.class).id(topicId).now();
        Topic topicToDelete = dm.getTopicWithTopicId(topicId);
        ObjectifyService.ofy().delete().entity(topicToDelete).now();
    }

    public void deleteMC(Long mcId){
        //ObjectifyService.ofy().delete().type(MultipleChoices.class).id(mcId).now();
        MultipleChoices mcToDelete = dm.getMultipleChoiceFromMultipleChoiceID(mcId);
        ObjectifyService.ofy().delete().entity(mcToDelete).now();
    }
}
