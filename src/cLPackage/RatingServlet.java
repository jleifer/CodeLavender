package cLPackage;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
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
 * Created by Randhawa on 5/10/17.
 */
public class RatingServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int rating = Integer.parseInt(req.getParameter("rating"));
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");

        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        //get the module object
        Course course = null;
        DataManager dm = DataManager.getDataManager();
        course = dm.getCourseWithCourseId(courseId);

        int currentSum = course.getEndorsedByUsers() * course.getTotalEndorsers();
        currentSum += rating;
        int newNumEndorsers = course.getTotalEndorsers() + 1;
        int newRating = currentSum / newNumEndorsers;

        dm.updateCourse(userId, courseId, course.getName(), course.getDescription(),
                course.getImgURL(), course.getIsPublic(), newRating, newNumEndorsers,
                course.getEndorsedByInstructors());

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("viewCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}
