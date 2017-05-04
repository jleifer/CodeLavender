package cLPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModuleController {

    @RequestMapping(value = {"/newModule.jsp","/newModule"}, method = RequestMethod.GET)
    public String getProfilePage(ModelMap model) {
        return "newModule"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}