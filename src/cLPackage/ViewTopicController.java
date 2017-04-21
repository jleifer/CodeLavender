package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Jonathan on 4/21/2017.
 */
@Controller
public class ViewTopicController {

    @RequestMapping(value = {"/viewTopic", "/viewTopic.jsp"}, method = RequestMethod.GET)
    public String getCoursePage(ModelMap model) {
        return "viewTopic";
    }
}