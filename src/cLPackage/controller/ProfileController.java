package cLPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @RequestMapping(value = {"/profile.jsp","/profile"}, method = RequestMethod.GET)
    public String getProfilePage(ModelMap model,
                                 @ModelAttribute("userId")String userId) {

        model.addAttribute("userId",userId);

        return "profile"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}