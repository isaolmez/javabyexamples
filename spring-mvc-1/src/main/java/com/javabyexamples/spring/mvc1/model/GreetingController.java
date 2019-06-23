package com.javabyexamples.spring.mvc1.model;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ModelAndView get(@RequestParam("name") String name) {
        Map<String, Object> modelMap = new HashMap<>();
        Greeting greeting = new Greeting(name);
        modelMap.put("greeting", greeting);

        return new ModelAndView("greet", modelMap);
    }

    @RequestMapping(path = "/getWithModelMap", method = RequestMethod.GET)
    public String getWithModelMap(@RequestParam("name") String name, ModelMap modelMap) {
        Greeting greeting = new Greeting(name);
        modelMap.addAttribute("greeting", greeting);

        return "greet";
    }

    @RequestMapping(path = "/getWithModel", method = RequestMethod.GET)
    public String getWithModel(@RequestParam("name") String name, Model model) {
        Greeting greeting = new Greeting(name);
        model.addAttribute("greeting", greeting);

        return "greet";
    }

    @RequestMapping(path = "/getWithMap", method = RequestMethod.GET)
    public String getWithMap(@RequestParam("name") String name, Map<String, Object> model) {
        Greeting greeting = new Greeting(name);
        model.put("greeting", greeting);

        return "greet";
    }
}
