package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/search.jsp","/search"})
public class SearchCntrl {
    @RequestMapping(method = RequestMethod.GET)
    public String getSearchPage(ModelMap model) {
        return "search"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }
}