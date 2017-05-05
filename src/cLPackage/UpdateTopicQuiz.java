/**
 * Created by Yifang on 2/11/2017.
 */
package cLPackage;

import cLPackage.dataStore.Topic;
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

public class UpdateTopicQuiz extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString= req.getParameter("moduleId");
        String topicIdIdString= req.getParameter("topicId");

        String topic_name =req.getParameter("topic_name");
        String topic_description =req.getParameter("topic_description");

        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdIdString);
        //get the module object
        Topic topic = null;
        List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).list();
        for (int i = 0 ; i < topicList.size(); i++){
            if(topicList.get(i).id.longValue()==courseId.longValue()){
                topic = topicList.get(i);
            }
        }
        System.out.print("updating topic :"+topic.id);

        //change attributes
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        com.google.appengine.api.datastore.Key courseKey = KeyFactory.createKey("Course", courseId);
        Entity Topic = new Entity("Course",topic.id,courseKey);
        //Topic.setIndexedProperty("name",course_name);
        //Topic.setIndexedProperty("ownerFirst",course.getOwnerFirst());
        //Topic.setIndexedProperty("ownerLast",course.getOwnerLast());
        //Topic.setIndexedProperty("isPublic",isPubshed);
        //Topic.setIndexedProperty("endorsedByUsers",course.getEndorsedByUsers());
        //Topic.setIndexedProperty("endorsedByInstructors",course.getEndorsedByInstructors());
        ObjectifyService.ofy().delete().entity(topic).now();
        datastore.put(Topic);
        //delete it

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}