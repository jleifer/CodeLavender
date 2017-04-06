/**
 * Created by Spartanrme on 2/7/2017.
 * From https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
 */
package cLPackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello the test works!!!");
        model.addAttribute("returnlink", "HERE");
        return "main"; //Name of the jsp - using a different name will result in a different jsp being loaded.
    }

}