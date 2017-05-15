package cLPackage.controller;

import cLPackage.dataStore.DataManager;
import cLPackage.dataStore.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Yifang Cao on 5/15/2017.
 */
@Controller
public class AdminController {
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String adminLogin(HttpServletRequest req) {
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();


        return "adminLogin";
    }
    @RequestMapping(value = {"/adminUserManagement"}, method = RequestMethod.GET)
    public String adminUserManagement(HttpServletRequest req) {
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();

        //check if user is loged in
        if(req.getSession().getAttribute("admin_validated")==null
                ||(Boolean)req.getSession().getAttribute("admin_validated")==false){
            req.getSession().setAttribute("admin_validated",false);
            return "redirect:admin";
        }else{

            List<User> userList = dm.getUserList();
            req.getSession().setAttribute("userList",userList);
            return "adminUserManagement";
        }

    }

    @RequestMapping(value = {"/validateAdmin"}, method = RequestMethod.POST)
    public String validateAdmin(HttpServletRequest req,HttpServletResponse resp) {
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();
        String admin_name = req.getParameter("admin_name");
        String admin_password = req.getParameter("admin_password");
        System.out.println("name"+admin_name+",pas:"+admin_password);
        if(admin_name.equalsIgnoreCase("admin")
                && Integer.parseInt(admin_password)==8888){
            req.getSession().setAttribute("admin_validated",true);
            return "redirect:adminUserManagement";
        }else {
            req.getSession().setAttribute("admin_validated",false);
            return "redirect:admin";
        }
    }

    @RequestMapping(value = {"/adminMakeInstructor"}, method = RequestMethod.GET)
    public String adminMakeInstructor(HttpServletRequest req,HttpServletResponse resp) {
        /* Retrieve data manager */
        DataManager dm = DataManager.getDataManager();
        //check if user is loged in
        if(req.getSession().getAttribute("admin_validated")==null
                ||(Boolean)req.getSession().getAttribute("admin_validated")==false){
            req.getSession().setAttribute("admin_validated",false);
            return "redirect:admin";
        }else{
            String isInstructor = req.getParameter("isInstructor");
            String userId = req.getParameter("userId");
            System.out.println("id"+userId+"operation:"+isInstructor);
            dm.updateIsInstructor(Long.parseLong(userId),Integer.parseInt(isInstructor));
            return "redirect:adminUserManagement";
        }
    }
}
