package cLPackage.controller;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index","/index.jsp"}, method = RequestMethod.GET)
    public String getRootPage(ModelMap model) {
        return "index"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String logUserIn(HttpServletRequest req) {
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve needed user values from the request */
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        //Retrieve User entity from datastore
        User user = dm.getUserWithEmail(email);
        if (user == null) {
            /* Create new user entity in the datastore when a new user signs in */
            dm.createUser(firstName, lastName, email);
            user = dm.getUserWithEmail(email);
        }

        /* Set needed values into the session */
        req.getSession().setAttribute("userId", user.getId().toString());
        req.getSession().setAttribute("email", user.getEmail());
        req.getSession().setAttribute("firstName", user.getFirstName());
        req.getSession().setAttribute("lastName", user.getLastName());
        System.out.println("Servlet setting in session succeeded.");

        return "redirect:/main";
    }

    @RequestMapping(value = {"/main.jsp","/main"}, method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {

        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        /* Retrieve the list of all Course entities from the datastore */
        List<Course> courseList = dm.getCourseList();



        List<User> userList = dm.getUserList();
        ArrayList<Course> instructorCourseList  = new ArrayList<Course>();
        ArrayList<Course> userCourseList  = new ArrayList<Course>();
        for (int i =0; i<courseList.size();i++){
            Course course = courseList.get(i);
            // if private, skip this course.
            if(course.getIsPublic()==0){
                continue;
            }
            long userId = course.user.getId();
            User user = null;
            for(int k =0; k<userList.size();k++){
                if(userList.get(k).getId().longValue()==userId){
                    user = userList.get(k);
                }
            }
            if(user.getIsInstructor()==1){
                // is instructor, add to instructorCourseList
                instructorCourseList.add(course);
            }else{
                //not instrucotr, add to userCourseList
                userCourseList.add(course);
            }
        }
        /* Set attributes into the model for dynamic loading */
        model.addAttribute("courseList", courseList);
        model.addAttribute("instructorCourseList", instructorCourseList);
        model.addAttribute("userCourseList", userCourseList);


        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}