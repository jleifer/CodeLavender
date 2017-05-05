package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

    @RequestMapping(value = {"/search.jsp","/search"}, method = RequestMethod.GET)
    public String getSearchPage(ModelMap model,
                                @ModelAttribute("searchStr") String searchStr) {

        /* Retrieve Data manager */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve courses containing matching names from the datastore */
        List<Course> courseList = dm.getCourseListContainingName(searchStr);
        model.addAttribute("searchStr", searchStr);
        model.addAttribute("courseList", courseList);

        return "search"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}