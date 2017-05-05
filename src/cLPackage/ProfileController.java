package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ProfileController
 * The controller for the "profile" page.
 */
@Controller
@RequestMapping({"/profile.jsp","/profile"})
public class ProfileController {

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(ModelMap model,
                                 @ModelAttribute("userId")String userId) {
        //Give the profile page the user's userID for datastore use.
        model.addAttribute("userId",userId);

        return "profile"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}