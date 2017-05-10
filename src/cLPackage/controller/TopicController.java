package cLPackage.controller;

import cLPackage.dataStore.DataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Jonathan on 4/21/2017.
 * ViewTopicController
 * The controller for the "viewTopic" page.
 */
@Controller
public class TopicController {

    @RequestMapping(value = {"/viewTopic", "/viewTopic.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model) {
        return "viewTopic";
    }

    @RequestMapping(value = {"/newTopic.jsp","/newTopic"}, method = RequestMethod.GET)
    public String getNewTopicPage(ModelMap model) {
        return "newTopic"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

    @RequestMapping(value = {"deleteTopic"}, method = RequestMethod.GET)
    public String deleteTopic(ModelMap model,
                              @ModelAttribute("topicId") Long topicId) {
        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        model.addAttribute("moduleId", dm.getTopicParent(topicId));

        dm.deleteTopic(topicId);

        /* Set needed values into the session and model. */
        return "redirect:/editModule";
    }

    @RequestMapping(value = {"deleteMC"}, method = RequestMethod.GET)
    public String deleteMC(ModelMap model,
                           @ModelAttribute("mcID") Long mcId) {

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        model.addAttribute("topicId", dm.getMCParent(mcId));

        dm.deleteMC(mcId);

        /* Set needed values into the session and model. */
        return "redirect:/editTopic";
    }
}