package com.example.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","SJ");


        return "greetings"; //templates/greetings.mustache > browser
    }

    @GetMapping("/bye")
    public String SeeYouAgain(Model model){
        model.addAttribute("username","SJ");

        return "goodbye";
    }
}
