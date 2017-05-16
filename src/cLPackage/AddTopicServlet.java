package cLPackage;

import cLPackage.dataStore.*;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yifang Cao on 4/21/2017.
 * AddTopicServlet
 * The purpose of this servlet is to allow a user to add a topic to an existing module that they own.
 */
public class AddTopicServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);

        //Register classes first.
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);

        //Obtain the module that the topic will be added to.
        Key<Course> courseKey = Key.create(Course.class,Long.parseLong(courseIdString));
        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();
        Module module = null;
        System.out.println("module List Size:"+moduleList.size());
        for(int i = 0; i<moduleList.size();i++){
            System.out.println("modu id :"+moduleId +" and curID:"+moduleList.get(i).getId());
            if(moduleList.get(i).getId().longValue()==moduleId.longValue()){
                System.out.print("\nget this path\n");
                module = moduleList.get(i);
            }
        }

        //Create Topic.
        Topic newTopic = new Topic("Default Topic", 1, "No Content", ObjectifyService.ofy().load().entity(module).now());
        ObjectifyService.ofy().save().entity(newTopic).now();
        String options[] = {"True", "False"};
        MultipleChoices newMulti = new MultipleChoices("Default Question Text", 2, 1
                , options,ObjectifyService.ofy().load().entity(newTopic).now());
        ObjectifyService.ofy().save().entity(newMulti).now();

        //Set data for future use.
        resp.sendRedirect("newModule?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId);
    }
}
