package cLPackage.controller;

import cLPackage.dataStore.*;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Created by Jonathan on 4/21/2017.
 * ViewTopicController
 * The controller for the "viewTopic" page.
 */
@Controller
public class TopicController {

    @RequestMapping(value = {"/viewTopic", "/viewTopic.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model,
                                @ModelAttribute("curUserId") long curUserId) {
        model.addAttribute("curUserId",curUserId);
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
        model.addAttribute("cur_quiz_type", 4);
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

    @RequestMapping(value = {"/saveMC"}, method = RequestMethod.POST)
    public String saveMC(ModelMap model,
                           @ModelAttribute("mcId") Long mcId,
                           @ModelAttribute("topicId") Long topicId,
                         @RequestBody MultiValueMap<String, String> body){

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        System.out.println("Saving " + mcId);
        String qT = "quizDescription_"+mcId.toString();
        String ans = "quiz_"+mcId.toString()+"_ans";
        String questionText = body.get(qT).get(0);
       // int answer = body.get(ans).get

        dm.updateMC(mcId, questionText, 0);

        model.addAttribute("topicId", topicId);

        /* Set needed values into the session and model. */
        return "redirect:/editTopic";
    }

    @RequestMapping(value = {"/addMC"}, method = RequestMethod.POST)
    public String addMC(ModelMap model,
                           @ModelAttribute("topicId") Long topicId){

        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();
        dm.createTopicQuizQuestion(topicId, null);

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

    @RequestMapping(value = {"/TakeTopicQuizServlet", "/TakeTopicQuizServlet.jsp"}, method = RequestMethod.GET)
    public String TakeTopicQuizServlet(ModelMap model,
                                       HttpServletRequest req ) {
        String curUserIdString = req.getParameter("curUserId");
        String userIdString = req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        String topicIdString = req.getParameter("topicId");
        String topicProficiencyString = req.getParameter("topicProficiency");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        Long topicId = Long.parseLong(topicIdString);
        long curUserId = Long.parseLong(curUserIdString);
        int topicProficiency = Integer.parseInt(topicProficiencyString);
        //get the module object
        User user = null;
        List<User> userList = ObjectifyService.ofy().load().type(User.class).list();
        for (int i = 0 ; i < userList.size(); i++){
            if(userList.get(i).id.longValue()==userId.longValue()){
                user = userList.get(i);
            }
        }

        //clean up current topic proficiency score
        List<UserCompleted> userCompletedList = ObjectifyService.ofy().load().type(UserCompleted.class).list();
        for (int i = 0 ; i < userCompletedList.size(); i++){
            UserCompleted usercompleted =userCompletedList.get(i);
            long parentId=usercompleted.getParentUser().getId();
            long curTopicId = usercompleted.getTopicID();
            if(parentId==userId.longValue()&&curTopicId==topicId.longValue()){
                ObjectifyService.ofy().delete().entities(usercompleted).now();
            }
        }

        //create and record new topic proficiency into datastore
        UserCompleted usercompleted = new UserCompleted(
                courseId.longValue(),
                moduleId.longValue(),
                topicId.longValue(),
                0,
                0,
                topicProficiency,
                0,
                user);
        ObjectifyService.ofy().save().entity(usercompleted).now();



        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        model.addAttribute("moduleId",moduleId.longValue());
        model.addAttribute("topicId",topicId.longValue());
        model.addAttribute("curUserId",curUserId);

        return "viewTopic";
    }

}