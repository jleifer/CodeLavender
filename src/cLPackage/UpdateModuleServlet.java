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

public class UpdateModuleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        //get the module object
        Course course = null;
        List<Course> courseList = ObjectifyService.ofy().load().type(Course.class).list();
        for (int i = 0 ; i < courseList.size(); i++){
            if(courseList.get(i).id.longValue()==courseId.longValue()){
                course = courseList.get(i);
            }
        }
        System.out.print("updating "+course.id);

        //change attributes
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        com.google.appengine.api.datastore.Key userKey = KeyFactory.createKey("User", userId);
        Entity Course = new Entity("Course",course.id,userKey);
        Course.setIndexedProperty("name","Edited");
        Course.setIndexedProperty("ownerFirst",course.getOwnerFirst());
        Course.setIndexedProperty("ownerLast",course.getOwnerLast());
        Course.setIndexedProperty("isPublic",course.getIsPublic());
        Course.setIndexedProperty("endorsedByUsers",course.getEndorsedByUsers());
        Course.setIndexedProperty("endorsedByInstructors",course.getEndorsedByInstructors());
        Course.setIndexedProperty("description",course.getDescription());
        Course.setIndexedProperty("imgURL",course.getImgURL());
        ObjectifyService.ofy().delete().entity(course).now();
        datastore.put(Course);
        //delete it

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}