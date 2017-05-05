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
 * AddQuizServlet
 * The purpose of  this servlet is to allow a user to add a quiz to an existing
 * topic that they already own.
 */
public class AddQuizServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String userIdString = req.getParameter("userId");
        String courseIdString = req.getParameter("courseId");
        String moduleIdString = req.getParameter("moduleId");
        String topicIdString = req.getParameter("topicId");
        String quizType = req.getParameter("quizType");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdString);

        //Register classes first.
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);

        //Find the topic belonging to this quiz.
        List<Topic> TopicList = ObjectifyService.ofy().load().type(Topic.class).list();
        Topic topic = null;
        for(int i = 0; i<TopicList.size();i++){
            if(TopicList.get(i).getId().longValue()==topicId.longValue()){
                System.out.print("\nget this path\n");
                topic = TopicList.get(i);
            }
        }

        //Create Quiz.
        String options[] = {"True", "False"};
        System.out.print("quiz type :"+quizType);
        int optionNum = 2; //The number of options to a quiz question. Default two, never less then two.
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

        //Set data for future use.
        resp.sendRedirect("newTopic?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId+"&topicId="+topicId);
    }
}
