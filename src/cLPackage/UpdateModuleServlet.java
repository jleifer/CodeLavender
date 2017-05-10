/**
 * Created by Yifang on 2/11/2017.
 */
package cLPackage;

import cLPackage.dataStore.Course;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * UpdateCourseServlet
 * The purpose of this servlet is to allow a user to edit and update the information of an existing course
 * that they own.
 */
public class UpdateModuleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from the forms.
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");

        String course_name =req.getParameter("course_name");
        String course_description =req.getParameter("course_description");
        String img_url =req.getParameter("img_url");
        String isPubshed =req.getParameter("isPubshed");
        boolean isPubshed_bool = false;
        if(isPubshed.equals("1")){
            isPubshed_bool = true;
        }


        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);

        //Obtain the course from the datastore.
        Course course = null;
        List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
        for (int i = 0 ; i < courseList.size(); i++){
            if(courseList.get(i).id.longValue()==courseId.longValue()){
                course = courseList.get(i);
            }
        }
        System.out.print("updating "+course.id);

        //Change attributes.
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        com.google.appengine.api.datastore.Key userKey = KeyFactory.createKey("User", userId);
        Entity Course = new Entity("Course",course.id,userKey);
        Course.setIndexedProperty("name",course_name);
        Course.setIndexedProperty("ownerFirst",course.getOwnerFirst());
        Course.setIndexedProperty("ownerLast",course.getOwnerLast());
        Course.setIndexedProperty("isPublic",isPubshed);
        Course.setIndexedProperty("endorsedByUsers",course.getEndorsedByUsers());
        Course.setIndexedProperty("endorsedByInstructors",course.getEndorsedByInstructors());
        Course.setIndexedProperty("description",course_description);
        Course.setIndexedProperty("imgURL",img_url);
        ObjectifyService.ofy().delete().entity(course).now();
        datastore.put(Course);
        //delete it

        //Send user to the new course.
        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}