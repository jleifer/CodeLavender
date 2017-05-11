package cLPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jonathan on 4/21/2017.
 */
@Controller
public class CourseController {

    @RequestMapping(value = {"/viewCourse", "/viewCourse.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model,
                         @ModelAttribute("userId") String userId,
                         @ModelAttribute("courseId") String courseId) {
        model.addAttribute("userId", userId);
        model.addAttribute("courseId", courseId);
        return "course";
    }

    @RequestMapping(value = {"/newCourse.jsp","/newCourse"}, method = RequestMethod.GET)
    public String getNewCoursePage(ModelMap model) {
        return "newCourse"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}