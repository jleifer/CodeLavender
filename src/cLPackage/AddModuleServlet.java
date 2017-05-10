/**
 * Created by Yifang on 2/11/2017.
 * AddModuleServlet
 * The purpose of this servlet is to allow a user to add a module to an existing course that they own.
 */
package cLPackage;

import cLPackage.dataStore.*;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddModuleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String userIdString = req.getParameter("userId");
        String courseIdString = req.getParameter("courseId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);

        //Register classes first.
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);

        //Get current user object.
        User curUser = null;
        List<User> userList = ObjectifyService.ofy().load().type(User.class).list();
        for(int i = 0; i<userList.size();i++){
            if(userList.get(i).id.longValue() == userId.longValue()){
                curUser = userList.get(i);
            }
        }
        
        //Add module only.
        if(courseId.longValue()>0){
            //getCurrent course
            Course curCourse  =null;
            List<Course>courseList= ObjectifyService.ofy().load().type(Course.class).list();
            for (int i = 0 ; i< courseList.size();i++){
                if(courseList.get(i).id.longValue() == courseId.longValue()){
                    curCourse = courseList.get(i);
                }
            }
            //Create module / topic / quiz.
            Module newModule = new Module("Default Module", ObjectifyService.ofy().load().entity(curCourse).now());
            ObjectifyService.ofy().save().entity(newModule).now();
            Topic newTopic = new Topic("Default Topic", 0, "No Content", ObjectifyService.ofy().load().entity(newModule).now());
            ObjectifyService.ofy().save().entity(newTopic).now();
            String options[] = {"True", "False"};
            MultipleChoices newMulti = new MultipleChoices("Default Question Text", 2, 1
                    , options,ObjectifyService.ofy().load().entity(newTopic).now());
            ObjectifyService.ofy().save().entity(newMulti).now();

            //Set data for future use.
            HttpSession session = req.getSession();
            session.setAttribute("userId",userId.longValue());
            session.setAttribute("courseId",courseId.longValue());
            resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
        }else {

            //Create a course / module / topic for curUser, and save into datastore.
            Course newCourse = new Course("No Course Name", curUser.getFirstName(), curUser.getLastName(),
                    0, 0, 0, "This user is to lazy to put a description", curUser);
            ObjectifyService.ofy().save().entity(newCourse).now();

            Module newModule = new Module("Default Module", ObjectifyService.ofy().load().entity(newCourse).now());
            ObjectifyService.ofy().save().entity(newModule).now();

            Topic newTopic = new Topic("Default Topic", 0, "No Content", ObjectifyService.ofy().load().entity(newModule).now());
            ObjectifyService.ofy().save().entity(newTopic).now();

            String options[] = {"True", "False"};
            MultipleChoices newMulti = new MultipleChoices("Default Question Text", 2, 1
                    , options, ObjectifyService.ofy().load().entity(newTopic).now());
            ObjectifyService.ofy().save().entity(newMulti).now();

            //Get newCourse ID.
            Long newCourseId = ObjectifyService.ofy().load().entity(newCourse).now().id;

            //Set data for future use.
            HttpSession session = req.getSession();
            session.setAttribute("userId", userId.longValue());
            session.setAttribute("courseId", newCourseId.longValue());
            resp.sendRedirect("newCourse?userId=" + userId.longValue() + "&courseId=" + newCourseId.longValue());
        }
    }
}