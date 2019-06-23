package com.javabyexamples.spring.mvc1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

    @RequestMapping(path = "/visit", method = RequestMethod.GET)
    public String visit(@RequestParam("name") String name, ModelMap modelMap) {
        Message message = new Message("Hello", name);
        modelMap.addAttribute("message", message);

        return "homepage";
    }

    @RequestMapping(path = "/postComment", method = RequestMethod.POST)
    public String postComment(@RequestParam("username") String username, @RequestParam("comment") String comment, ModelMap modelMap) {
        // Store comment...
        Message message = new Message(comment, username);
        modelMap.addAttribute("message", message);

        return "comment";
    }

    @PostMapping(path = "/postCommentAgain")
    public String postCommentAgain(@RequestParam("username") String username, @RequestParam("comment") String comment, ModelMap modelMap) {
        // Store comment...
        Message message = new Message(comment, username);
        modelMap.addAttribute("message", message);

        return "comment";
    }

    @PostMapping(path = "/postCommentModelAttribute")
    public String postCommentModelAttribute(@ModelAttribute Message message, ModelMap modelMap) {
        // Store comment...
        modelMap.addAttribute("message", message);

        return "comment";
    }

    @PostMapping(path = "/postCommentAgain", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postCommentRequestBody(@RequestBody Message message, ModelMap modelMap) {
        // Store comment...
        modelMap.addAttribute("message", message);

        return "comment";
    }

    @GetMapping("/throws")
    public String throwingExcetion() {
        throw new RuntimeException("Planned exception");
    }

    //TODO
    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
