package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/main.jsp","/main"})
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String getMainPage(ModelMap model) {
        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}