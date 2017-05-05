package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * NewCourseController
 * The controller for the "newCourse" page.
 */
@Controller
@RequestMapping({"/newCourse.jsp","/newCourse"})
public class NewCourseController {
    @RequestMapping(method = RequestMethod.GET)
    public String getNewCoursePage(ModelMap model) {
        return "newCourse"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}