package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

        /* Retrieve the list of all Course entities from the datastore */
        List<Course> courseList = dm.getCourseList();

        /* Set attributes into the model for dynamic loading */
        System.out.print(userId);
        model.addAttribute("userId",userId);
        model.addAttribute("email",email);
        model.addAttribute("courseList", courseList);
        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}