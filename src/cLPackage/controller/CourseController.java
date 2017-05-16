package cLPackage.controller;

import cLPackage.dataStore.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 4/21/2017.
 */
@Controller
public class CourseController {

    @RequestMapping(value = {"/viewCourse", "/viewCourse.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model,
                                @SessionAttribute("userId") Long userId,
                                @ModelAttribute("courseId") Long courseId,
                                @ModelAttribute("curUserId") long curUserId) {
        DataManager dm = DataManager.getDataManager();
        //check if the course is rated by this user;
        UserRating userRating = dm.getUserRatingByUIDandCourseID(
                userId.longValue(),
                courseId.longValue());
        boolean isRated = false;
        if(userRating!=null){
            isRated = true;
        }
        model.addAttribute("isRated",isRated);
        model.addAttribute("courseId", courseId);
        model.addAttribute("curUserId",curUserId);
        return "course";
    }

    @RequestMapping(value = {"/newCourse"}, method = RequestMethod.GET)
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
        Course courseToEdit = null;
        try{
            courseToEdit = dm.getCourseWithCourseId(courseId);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            if(courseToEdit == null){
                /* Wait and try again */
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                courseToEdit = dm.getCourseWithCourseId(courseId);
            }
        }
        List<Module> moduleList = dm.getModulesFromCourse(courseId);
        String coursePublished = (courseToEdit.getIsPublic() == 0) ? "unchecked" : "checked";

        /* Set needed values into the session and model */
        model.addAttribute("courseToEdit", courseToEdit);
        model.addAttribute("coursePublished", coursePublished);
        model.addAttribute("moduleList", moduleList);
        return "editCourse";
    }

    @RequestMapping(value = {"/search.jsp", "/search"}, method = RequestMethod.GET)
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

    @RequestMapping(value = {"/deleteCourse"}, method = RequestMethod.GET)
    public String deleteCourse(ModelMap model,
                               @ModelAttribute("courseId") Long courseId) {
        /* Retrieve Data manager. */
        DataManager dm = DataManager.getDataManager();

        dm.deleteCourse(courseId);

        model.remove("courseId");

        /* Set needed values into the session and model. */
        return "redirect:/profile";
    }

    @RequestMapping(value = {"/InstructorCourse"}, method = RequestMethod.GET)
    public String InstructorCourse(ModelMap model) {
        /* Retrieve Data manager. */
         /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve the list of all Course entities from the datastore */
        List<Course> courseList = dm.getCourseList();
        List<User> userList = dm.getUserList();
        ArrayList<Course> instructorCourseList  = new ArrayList<Course>();
        for (int i =0; i<courseList.size();i++){
            Course course = courseList.get(i);
            // if private, skip this course.
            if(course.getIsPublic()==0){
                continue;
            }
            long userId = course.getTheParentUser().getId();
            User user = null;
            for(int k =0; k<userList.size();k++){
                if(userList.get(k).getId().longValue()==userId){
                    user = userList.get(k);
                }
            }
            if(user.getIsInstructor()==1){
                // is instructor, add to instructorCourseList
                instructorCourseList.add(course);
            }
        }
        /* Set attributes into the model for dynamic loading */
        model.addAttribute("courseList", courseList);
        model.addAttribute("instructorCourseList", instructorCourseList);
        return "InstructorCourse";
    }

    @RequestMapping(value = {"/UserCourse"}, method = RequestMethod.GET)
    public String UserCourse(ModelMap model) {
        /* Retrieve Data manager. */
         /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve the list of all Course entities from the datastore */
        List<Course> courseList = dm.getCourseList();
        List<User> userList = dm.getUserList();
        ArrayList<Course> userCourseList  = new ArrayList<Course>();
        for (int i =0; i<courseList.size();i++){
            Course course = courseList.get(i);
            // if private, skip this course.
            if(course.getIsPublic()==0){
                continue;
            }
            long userId = course.getTheParentUser().getId();
            User user = null;
            for(int k =0; k<userList.size();k++){
                if(userList.get(k).getId().longValue()==userId){
                    user = userList.get(k);
                }
            }
            if(user.getIsInstructor()==0){
                // is not instructor, add to instructorCourseList
                userCourseList.add(course);
            }
        }
        /* Set attributes into the model for dynamic loading */
        model.addAttribute("courseList", courseList);
        model.addAttribute("userCourseList", userCourseList);
        return "UserCourse";
    }

}