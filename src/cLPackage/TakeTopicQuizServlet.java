package cLPackage;

import cLPackage.dataStore.User;
import cLPackage.dataStore.UserCompleted;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by AjaxSurangama on 5/14/2017.
 */
public class TakeTopicQuizServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectifyService.register(UserCompleted.class);
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        String topicIdString = req.getParameter("topicId");
        String topicProficiencyString = req.getParameter("topicProficiency");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdString);
        int topicProficiency = Integer.parseInt(topicProficiencyString);
        //get the module object
        User user = null;
        List<User> userList = ObjectifyService.ofy().load().type(User.class).list();
        for (int i = 0 ; i < userList.size(); i++){
            if(userList.get(i).id.longValue()==userId.longValue()){
                user = userList.get(i);
            }
        }

        //clean up current topic proficiency score
        List<UserCompleted> userCompletedList = ObjectifyService.ofy().load().type(UserCompleted.class).list();
        for (int i = 0 ; i < userCompletedList.size(); i++){
            UserCompleted usercompleted =userCompletedList.get(i);
            long parentId=usercompleted.getParentUser().getId();
            long curTopicId = usercompleted.getTopicID();
            if(parentId==userId.longValue()&&curTopicId==topicId.longValue()){
                ObjectifyService.ofy().delete().entities(usercompleted).now();
            }
        }

        //create and record new topic proficiency into datastore
        UserCompleted usercompleted = new UserCompleted(
                courseId.longValue(),
                moduleId.longValue(),
                topicId.longValue(),
                0,
                0,
                topicProficiency,
                0,
                user);
        ObjectifyService.ofy().save().entity(usercompleted).now();

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        session.setAttribute("moduleId",moduleId.longValue());
        session.setAttribute("topicId",topicId.longValue());

        resp.sendRedirect("viewTopic?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId.longValue()+"&topicId="+topicId.longValue());
    }
}
