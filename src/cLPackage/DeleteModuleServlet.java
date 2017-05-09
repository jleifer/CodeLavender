/**
 * Created by Yifang on 2/11/2017.
 * DeleteModuleServlet
 * The purpose of this servlet is to allow a user to delete a module that they own.
 * This will also delete any topics that belong to said module.
 */
package cLPackage;

import cLPackage.dataStore.Module;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteModuleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Obtaining data from forms.
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);

        //Obtain the module from the datastore.
        Module module = null;
        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).list();
        for (int i = 0 ; i < moduleList.size(); i++){
            if(moduleList.get(i).id.longValue()==moduleId.longValue()){
               module = moduleList.get(i);
               //Now let's delete all the topics and topic quizzes associated.
            }
        }
        System.out.print("deleting "+module.id);

        //Delete it.
        ObjectifyService.ofy().delete().entity(module).now();

        //Send user to the course belonging to the module.
        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}