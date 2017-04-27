package cLPackage;

import cLPackage.dataStore.*;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by AjaxSurangama on 4/21/2017.
 */
public class AddQuizServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString = req.getParameter("userId");
        String courseIdString = req.getParameter("courseId");
        String moduleIdString = req.getParameter("moduleId");
        String topicIdString = req.getParameter("topicId");
        String quizType = req.getParameter("quizType");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdString);
        //register classes first
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);


        List<Topic> TopicList = ObjectifyService.ofy().load().type(Topic.class).list();
        Topic topic = null;
        for(int i = 0; i<TopicList.size();i++){
            if(TopicList.get(i).getId().longValue()==topicId.longValue()){
                System.out.print("\nget this fking path\n");
                topic = TopicList.get(i);
            }
        }

        //create Quize
        String options[] = {"True", "False"};
        System.out.print("quiz type :"+quizType);
        int optionNum = 2;
        if(!quizType.equals("TorF")){
            optionNum = Integer.parseInt(quizType.split("_")[1]);
            options = new String[optionNum];
            for (int i = 0; i<optionNum;i++){
                options[i]= new String("Option "+ (i+1));
            }
            System.out.print("option Num:"+optionNum);
            System.out.println("options are :"+options.length);
            System.out.println("topic :"+topic.getContent());
        }
        for (int i = 0 ; i<optionNum;i++){
            System.out.print("option co:"+options[i]);
        }
        MultipleChoices newMulti = new MultipleChoices("Default Question Text", options.length, 1
                , options, ObjectifyService.ofy().load().entity(topic).now());
        ObjectifyService.ofy().save().entity(newMulti).now();

        //set data for future use
        resp.sendRedirect("newTopic?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId+"&topicId="+topicId);
    }
}
