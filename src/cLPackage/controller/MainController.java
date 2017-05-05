package cLPackage.controller;

import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index","/index.jsp"}, method = RequestMethod.GET)
    public String getRootPage(ModelMap model) {
        return "index"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

    @RequestMapping(value = {"/main.jsp","/main"}, method = RequestMethod.GET)
    public String getMainPage(ModelMap model,
                              @ModelAttribute("email")String email,
                              @ModelAttribute("firstName")String firstName,
                              @ModelAttribute("lastName")String lastName) {
        //register class first
        /*ObjectifyService.register(User.class);

        //prepare paras for creating new user.
        Date date = new Date();
        int isInstructor = 0;

        //first check if the user has already exists
        boolean isUsrExist = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().isEmpty();
        Long userId;
        if(isUsrExist==true){
            //create new User Object and load into datastore
            User user = new User(firstName,lastName,isInstructor,email,date,null);
            ObjectifyService.ofy().save().entity(user).now();
            userId = ObjectifyService.ofy().load().entity(user).now().id;
        }else{
            userId = ObjectifyService.ofy().load().type(User.class).filter("email = ",email).list().get(0).id;
        }*/
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        //Retrieve User entity from datastore
        User user = dm.getUserWithEmail(email);
        if (user == null) {
            /* Create new user entity in the datastore when a new user signs in */
            dm.createUser(firstName, lastName, email);
            user = dm.getUserWithEmail(email);
        }
        Long userId = user.getId();
        System.out.print(userId);
        model.addAttribute("userId",userId);
        model.addAttribute("email",email);
        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}