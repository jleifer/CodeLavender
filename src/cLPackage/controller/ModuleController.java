package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.Module;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * NewModuleController
 * The controller for the "newModule" page.
 */
@Controller
public class ModuleController {

    @RequestMapping(value = {"/newModule.jsp","/newModule"}, method = RequestMethod.GET)
    public String createNewModule(ModelMap model,
                                 @ModelAttribute("courseId") Long courseId) {

        /* Retrieve Data manager to create new course */
        DataManager dm = DataManager.getDataManager();

        Course currentCourse = dm.getCourseWithCourseId(courseId);
        dm.createModule(courseId, currentCourse);

        model.addAttribute("courseId", courseId);

        return "redirect:/editCourse";
    }

    @RequestMapping(value = {"/deleteModule"}, method = RequestMethod.GET)
    public String deleteModule(ModelMap model,
                               @ModelAttribute("moduleId") Long moduleId) {
        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();

        /* Get parent course id to allow for redirect after deletion */
        model.addAttribute("courseId", dm.getModuleParent(moduleId));

        dm.deleteModule(moduleId);
        model.remove("moduleId");

        /* Set needed values into the session and model. */
        return "redirect:/editCourse";
    }
}