package cLPackage;

import cLPackage.dataStore.*;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping({"/newCourse.jsp","/newCourse"})
public class NewCourseController {

    @RequestMapping(method = RequestMethod.GET)
    public String getNewCoursePage(ModelMap model,
                                   @ModelAttribute("userId")String userId) {

        //register classes first
        ObjectifyService.register(User.class);
        ObjectifyService.register(Course.class);
        ObjectifyService.register(Module.class);
        ObjectifyService.register(Topic.class);
        ObjectifyService.register(MultipleChoices.class);

        //get current user object
        User curUser = ObjectifyService.ofy().load().type(User.class).id(Long.parseLong(userId)).now();

        //create a course / module / topic for curUser, and save into datastore.
        Course newCourse = new Course("No Course Name", curUser.getFirstName(), curUser.getLastName(),
                0, 0,0,"This user is to lazy to put a description", curUser);
        System.out.print(newCourse.getName());
        ObjectifyService.ofy().save().entity(newCourse).now();
        Module newModule = new Module("Default Module",newCourse);
        ObjectifyService.ofy().save().entity(newModule).now();
        Topic newTopic = new Topic("Default Topic",0,"No Content",newModule);
        ObjectifyService.ofy().save().entity(newTopic).now();
        String options[] = {"True","False"};
        MultipleChoices newMulti= new MultipleChoices("Default Question Text",2,1,options);
        ObjectifyService.ofy().save().entity(newMulti).now();


        //get newCourse ID,
        Long newCourseId = ObjectifyService.ofy().load().entity(newCourse).now().id;

        //set data for future use
        List<Module> modules = ObjectifyService.ofy().load().type(Module.class).ancestor(curUser).list();
        model.addAttribute("userId",Long.parseLong(userId));
        model.addAttribute("courseId",newCourseId);
        model.addAttribute("courseObj",newCourse);
        return "newCourse"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}