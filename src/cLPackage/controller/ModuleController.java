package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.Module;
import cLPackage.dataStore.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * NewModuleController
 * The controller for the "newModule" page.
 */
@Controller
public class ModuleController {

    @RequestMapping(value = {"/editModule.jsp","/newModule"}, method = RequestMethod.GET)
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

    @RequestMapping(value = {"/editModule"}, method = RequestMethod.GET)
    public String editModule(ModelMap model,
                             @ModelAttribute("moduleId") Long moduleId) {

        /* Retrieve Data manager */
        DataManager dm = DataManager.getDataManager();
        Module moduleToEdit = null;
        try{
            moduleToEdit = dm.getModuleWithModuleId(moduleId);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            if(moduleToEdit == null){
                /* Wait and try again */
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moduleToEdit = dm.getModuleWithModuleId(moduleId);
            }
        }
        List<Topic> topicList = dm.getTopicsFromModule(moduleId);
        String moduleHasTest = (moduleToEdit.getHasTest() == 0) ? "unchecked" : "checked";

        /* Set needed values into the session and model */
        model.addAttribute("moduleToEdit", moduleToEdit);
        model.addAttribute("topicList", topicList);
        model.addAttribute("moduleHasTest", moduleHasTest);
        model.addAttribute("courseId", moduleToEdit.getTheParentCourse().getId());

        return "editModule";
    }

    @RequestMapping(value = {"/updateModule"}, method = RequestMethod.POST)
    public String updateModule(ModelMap model,
                               @ModelAttribute("moduleId") Long moduleId,
                               @RequestBody MultiValueMap<String, String> body) {
        /* Retrieve Data manager to create new course */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve parameters needed to update module */
        String moduleEditName = body.get("moduleEditName").get(0);
        int hasTest = (body.keySet().contains("moduleEditHasTest")) ? 1 : 0;

        dm.updateModule(moduleId, moduleEditName, hasTest);

        /* Return to the edit module page with the changes committed */
        model.addAttribute("moduleId", moduleId);
        return "redirect:/editModule";
    }
}