/**
 * Created by Yifang on 2/11/2017.
 */
package cLPackage;

import cLPackage.dataStore.Course;
import cLPackage.dataStore.Module;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UpdateModuleName extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userIdString =req.getParameter("userId");
        String courseIdString =req.getParameter("courseId");
        String moduleIdString =req.getParameter("moduleId");
        int hasTest = Integer.parseInt(req.getParameter("hasTest"));

        String module_name =req.getParameter("module_name");


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
        System.out.print("updating module"+module.id);

        //change attributes
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        com.google.appengine.api.datastore.Key courseKey = KeyFactory.createKey("Course", courseId);
        Entity Module = new Entity("Module",module.id,courseKey);
        Module.setIndexedProperty("name",module_name);
        Module.setIndexedProperty("hasTest",hasTest);
        ObjectifyService.ofy().delete().entity(module).now();
        datastore.put(Module);
        //delete it

        HttpSession session = req.getSession();
        session.setAttribute("userId",userId.longValue());
        session.setAttribute("courseId",courseId.longValue());
        resp.sendRedirect("newModule?userId="+userId.longValue()+"&courseId="+courseId.longValue()+"&moduleId="+moduleId.longValue());
    }
}