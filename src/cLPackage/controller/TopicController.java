package cLPackage.controller;

import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.MultipleChoices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cLPackage.dataStore.Topic;


import java.util.List;
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

    @RequestMapping(value = {"/editTopic.jsp","/newTopic"}, method = RequestMethod.GET)
    public String getNewTopicPage(ModelMap model,
                                  @ModelAttribute("moduleId") Long moduleId) {
        DataManager dm = DataManager.getDataManager();
        dm.createTopic(moduleId, null);

        model.addAttribute("moduleId", moduleId);

        return "redirect:/editModule"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

    @RequestMapping(value = {"/editTopic"}, method = RequestMethod.GET)
    public String editTopic(ModelMap model,
                             @ModelAttribute("topicId") Long topicId) {

        /* Retrieve Data manager */
        DataManager dm = DataManager.getDataManager();
        Topic topicToEdit = null;
        try{
            topicToEdit = dm.getTopicWithTopicId(topicId);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            if(topicToEdit == null){
                /* Wait and try again */
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                topicToEdit = dm.getTopicWithTopicId(topicId);
            }
        }
        List<MultipleChoices> quizList = dm.getMCFromTopic(topicId);
        String topicHasTest = (topicToEdit.getHasTest() == 0) ? "unchecked" : "checked";

        /* Set needed values into the session and model */
        model.addAttribute("topicToEdit", topicToEdit);
        model.addAttribute("quizList", quizList);
        model.addAttribute("topicHasTest", topicHasTest);
        model.addAttribute("moduleId", topicToEdit.getTheParentModule().getId());
        model.addAttribute("quizSize", quizList.size());
        return "editTopic";
    }
    @RequestMapping(value = {"/deleteTopic"}, method = RequestMethod.GET)
    public String deleteTopic(ModelMap model,
                              @ModelAttribute("topicId") Long topicId) {
        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        model.addAttribute("moduleId", dm.getTopicParent(topicId));

        dm.deleteTopic(topicId);

        /* Set needed values into the session and model. */
        return "redirect:/editModule";
    }

    @RequestMapping(value = {"/deleteMC"}, method = RequestMethod.GET)
    public String deleteMC(ModelMap model,
                           @ModelAttribute("mcId") Long mcId,
                            @ModelAttribute("topicId") Long topicId){

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        System.out.println("Deleting " + mcId);

        /* Add the model attribute before we delete the MC */
        model.addAttribute("topicId", topicId);

        dm.deleteMC(mcId);

        /* Set needed values into the session and model. */
        return "redirect:/editTopic";
    }

    @RequestMapping(value = {"/addMC"}, method = RequestMethod.GET)
    public String addMC(ModelMap model,
                           @ModelAttribute("topicId") Long topicId) {

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        dm.createTopicQuizQuestion(topicId, null);

        /* Add the model attribute before we delete the MC */
        model.addAttribute("topicId", topicId);

        /* Set needed values into the session and model. */
        return "redirect:/editTopic";
    }

    @RequestMapping(value = {"/updateTopic"}, method = RequestMethod.POST)
    public String updateTopic(ModelMap model,
                               @ModelAttribute("topicId") Long topicId,
                               @RequestBody MultiValueMap<String, String> body) {

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        System.out.println("Updating topic...");
        /* Retrieve parameters needed to update topic. */
        String topicEditName = body.get("topicEditName").get(0);
        String topicContent = body.get("topic_text").get(0);
        int hasTest = (body.keySet().contains("topicEditName")) ? 1 : 0;

        dm.updateTopic(topicId, topicEditName, topicContent, hasTest);

        /* Return to the edit module page with the changes committed. */
        model.addAttribute("topicId", topicId);
        return "redirect:/editTopic";
    }
}