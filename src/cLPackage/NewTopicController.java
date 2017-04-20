package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/newTopic.jsp","/newTopic"})
public class NewTopicController {

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        return "newTopic"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}