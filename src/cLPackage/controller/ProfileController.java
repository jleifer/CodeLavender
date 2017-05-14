package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.UserCompleted;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @RequestMapping(value = {"/profile.jsp","/profile"}, method = RequestMethod.GET)
    public String getProfilePage(ModelMap model,
                                 @SessionAttribute("userId")String userId) {
        /* Retrieve datastore manager */
        DataManager dm = DataManager.getDataManager();
        Long Userid = Long.parseLong(userId);

        /* Retrieve the list of courses created by the user with the given id */
        List<Course> courseList = dm.getCourseListCreatedByUserID(Userid);

        /* Retrieve the list of courses created by the user with the given id */
        List<UserCompleted> userCompletedList = dm.getUserCompletedListCreatedByUserID(Userid);
        System.out.println("userCompletedList SIze: "+userCompletedList.size());
        /* Find courses started */
        ArrayList<Course> courseStarted = new ArrayList<Course>();
        ArrayList<Long> courseStartedId = new ArrayList<Long>();

        for(int i =0; i<userCompletedList.size();i++){
            long courseId = userCompletedList.get(i).getCourseID();
            for (int k = 0 ; k<courseList.size();k++){
                System.out.println(courseId+" vs "+courseList.get(k).id.longValue());
                if(courseId==courseList.get(k).id.longValue()){
                    if(!courseStartedId.contains(courseList.get(k).id.longValue())){
                        courseStartedId.add(courseList.get(k).id.longValue());
                        courseStarted.add(courseList.get(k));
                        break;
                    }
                }
            }
        }
        System.out.println("courseStarted Size:"+courseStarted.size());

        /* Add them to the model to load */
        model.addAttribute("userId",userId);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseStarted",courseStarted);
        return "profile"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}