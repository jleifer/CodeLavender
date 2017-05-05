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
public class ProfileController {

    @RequestMapping(value = {"/profile.jsp","/profile"}, method = RequestMethod.GET)
    public String getProfilePage(ModelMap model,
                                 @ModelAttribute("userId")String userId) {
        /* Retrieve datastore manager */
        DataManager dm = DataManager.getDataManager();
        Long Userid = Long.parseLong(userId);

        /* Retrieve the list of courses created by the user with the given id */
        List<Course> courseList = dm.getCourseListCreatedByUserID(Userid);

        /* Add them to the model to load */
        model.addAttribute("userId",userId);
        model.addAttribute("courseList", courseList);

        return "profile"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}