package cLPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Jonathan on 4/21/2017.
 */
@Controller
public class CourseController {

    @RequestMapping(value = {"/viewCourse", "/viewCourse.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model) {
        return "course";
    }

    @RequestMapping(value = {"/newCourse.jsp","/newCourse"}, method = RequestMethod.GET)
    public String getNewCoursePage(ModelMap model) {
        return "newCourse"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}