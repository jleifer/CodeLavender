/**
 * Created by Yifang on 2/11/2017.
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

public class UpdateModuleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        Long userId = Long.parseLong(userIdString);
        Long courseId = Long.parseLong(courseIdString);
        Long moduleId = Long.parseLong(moduleIdString);
        //get the module object
        Module module = null;
        List<Module> moduleList = ObjectifyService.ofy().load().type(Module.class).list();
        for (int i = 0 ; i < moduleList.size(); i++){
            if(moduleList.get(i).id.longValue()==moduleId.longValue()){
                module = moduleList.get(i);
            }
        }
        System.out.print("deleting "+module.id);
        //delete it
        ObjectifyService.ofy().delete().entity(module).now();

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newCourse?userId="+userId.longValue()+"&courseId="+courseId.longValue());
    }
}