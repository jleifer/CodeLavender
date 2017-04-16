package cLPackage;

import cLPackage.dataStore.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Spartanrme on 4/16/2017.
 */
@Controller
public class CourseController {
    @RequestMapping(value = "/courseCreate", method = RequestMethod.GET)
    public String courseCreate(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "/courseCreate";
    }
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("addCourse")Course course, ModelMap model){
        model.addAttribute("name", course.getName());
        return "result";
    }
}
