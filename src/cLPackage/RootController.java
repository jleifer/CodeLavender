/**
 * Created by Jonathan on 4/6/2017.
 */
package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

/**
 * RootController
 * The controller for the "index" page.
 */
@Controller
@RequestMapping({"/", "/index","/index.jsp"})
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public String getRootPage(ModelMap model) {
        return "index"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

}
