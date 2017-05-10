package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.Module;
import cLPackage.dataStore.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonathan on 4/21/2017.
 */
@Controller
public class CourseController {

    @RequestMapping(value = {"/viewCourse", "/viewCourse.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model) {
        return "course";
    }

    @RequestMapping(value = {"/newCourse", "/newCourse.jsp"}, method = RequestMethod.GET)
    public String createNewCourse(ModelMap model,
                                  @SessionAttribute("userId") Long userId) {

        /* Retrieve Data manager to create new course */
        DataManager dm = DataManager.getDataManager();
        Course newCourse = dm.createCourse(userId);

        model.addAttribute("courseId", newCourse.getId());

        return "redirect:/editCourse";
    }

    @RequestMapping(value = {"/updateCourse"}, method = RequestMethod.POST)
    public String updateCourse(ModelMap model,
                               @SessionAttribute("userId") Long userId,
                               @ModelAttribute("courseId") Long courseId,
                               @RequestBody MultiValueMap<String, String> body) {

        /* Retrieve Data manager to create new course */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve parameters needed to update course */
        String courseEditName = body.get("courseEditName").get(0);
        String courseEditDescription = body.get("courseEditDescription").get(0);
        String courseEditImgURL = body.get("courseEditImgURL").get(0);
        int isPublic = (body.keySet().contains("courseEditIsPublic")) ? 1 : 0;


        dm.updateCourse(userId, courseId, courseEditName, courseEditDescription, courseEditImgURL, isPublic);

        /* Return to the edit course page with the changes committed */
        model.addAttribute("courseId", courseId);
        return "redirect:/editCourse";
    }

    @RequestMapping(value = {"/editCourse"}, method = RequestMethod.GET)
    public String editCourse(ModelMap model,
                             @ModelAttribute("courseId") Long courseId) {
        /* Retrieve Data manager */
        DataManager dm = DataManager.getDataManager();

        Course courseToEdit = dm.getCourseWithCourseId(courseId);
        List<Module> moduleList = dm.getModulesFromCourse(courseId);
        String coursePublished = (courseToEdit.getIsPublic()==0) ? "unchecked" : "checked";

        /* Set needed values into the session and model */
        model.addAttribute("courseToEdit", courseToEdit);
        model.addAttribute("coursePublished", coursePublished);
        model.addAttribute("moduleList", moduleList);
        return "editCourse";
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