package cLPackage.controller;

import cLPackage.dataStore.DataManager;
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
    public String getProfilePage(ModelMap model) {
        return "newModule"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

    @RequestMapping(value = {"/deleteModule"}, method = RequestMethod.GET)
    public String deleteModule(ModelMap model,
                               @ModelAttribute("moduleId") Long moduleId) {
        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        model.addAttribute("courseId", dm.getModuleParent(moduleId));

        dm.deleteModule(moduleId);

        /* Set needed values into the session and model. */
        return "redirect:/editCourse";
    }
}