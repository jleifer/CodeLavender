package cLPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
}