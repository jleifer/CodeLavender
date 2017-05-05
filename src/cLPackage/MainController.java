package cLPackage;

import cLPackage.dataStore.User;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * MainController
 * The purpose of this controller is to register new users who log in to the website if they don't already exist.
 * The controller will also save the user ID and email for use throughout the website.
 */
@Controller
@RequestMapping({"/main.jsp","/main"})
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(ModelMap model,
                              @ModelAttribute("email")String email,
                              @ModelAttribute("firstName")String firstName,
                              @ModelAttribute("lastName")String lastName) {
        //Register class first.
        ObjectifyService.register(User.class);

        //Prepare paras for creating new user.
        Date date = new Date();
        int isInstructor = 0;

        //First check if the user has already exists.
        boolean isUsrExist = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().isEmpty();
        // True if the datastore does not find the user (need a new user), otherwise false (user exists).
        Long userId;
        if(isUsrExist==true){
            //Create new User Object and load into datastore.
            User user = new User(firstName,lastName,isInstructor,email,date,null);
            ObjectifyService.ofy().save().entity(user).now();
            userId = ObjectifyService.ofy().load().entity(user).now().id;
        }else{
            userId = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0).id;
        }

        //Save user id for further use.
        System.out.print(userId);
        model.addAttribute("userId",userId);
        model.addAttribute("email",email);
        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}