package cLPackage.dataStore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Randhawa on 4/21/17.
 */
@Controller
@RequestMapping({"/search.jsp","/search"})
public class SearchCntrl {
    @RequestMapping(method = RequestMethod.GET)
    public String getNewCoursePage(ModelMap model) {
        return "search"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}
