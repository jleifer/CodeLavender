package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/profile.jsp","/profile"})
public class ProfileController {

    @RequestMapping(method = RequestMethod.GET)
    public String getProfilePage(ModelMap model) {
        return "profile"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}