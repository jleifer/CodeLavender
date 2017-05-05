package cLPackage;

import cLPackage.dataStore.Topic;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yifang Cao on 4/21/2017.
 * DeleteTopicServlet
 * The purpose of this servlet is to allow a user to delete a topic that they own from a module.
 */
public class DeleteTopicServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String topicIdString =req.getParameter("topicId");
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        Long topicId = Long.parseLong(topicIdString);
        Topic topic = null;

        //Obtain the topic from the datastore.
        List<Topic> topicList = ObjectifyService.ofy().load().type(Topic.class).list();
        for (int i = 0 ; i < topicList.size(); i++){
            if(topicList.get(i).id.longValue()==topicId.longValue()){
                topic = topicList.get(i);
            }
        }
        System.out.print("deleting "+topic.id);

        //Delete it.
        ObjectifyService.ofy().delete().entity(topic).now();

        //Return the user to the module.
        HttpSession session = req.getSession();
        resp.sendRedirect("newModule?userId="+userIdString+"&courseId="+courseIdString+"&moduleId="+moduleIdString);
    }
}
