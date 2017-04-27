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
 */
public class AddTopicServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        //register classes first
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);

        Key<Course> courseKey = Key.create(Course.class,Long.parseLong(courseIdString));
        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).ancestor(courseKey).list();
        Module module = null;
        System.out.println("module List Size:"+moduleList.size());
        for(int i = 0; i<moduleList.size();i++){
            System.out.println("modu id :"+moduleId +" and curID:"+moduleList.get(i).getId());
            if(moduleList.get(i).getId().longValue()==moduleId.longValue()){
                System.out.print("\nget this fking path\n");
                module = moduleList.get(i);
            }
        }

        //create Topic
        Topic newTopic = new Topic("Default Topic", 0, "No Content", ObjectifyService.ofy().load().entity(module).now());
        ObjectifyService.ofy().save().entity(newTopic).now();
        String options[] = {"True", "False"};
        MultipleChoices newMulti = new MultipleChoices("Default Question Text", 2, 1
                , options,ObjectifyService.ofy().load().entity(newTopic).now());
        ObjectifyService.ofy().save().entity(newMulti).now();

        //set data for future use
        resp.sendRedirect("newModule?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId);
    }
}
