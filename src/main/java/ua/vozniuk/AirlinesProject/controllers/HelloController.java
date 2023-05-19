package ua.vozniuk.AirlinesProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.vozniuk.AirlinesProject.security.PersonDetails;
import ua.vozniuk.AirlinesProject.services.PersonDetailService;

@Controller
public class HelloController {
    private final PersonDetailService personDetailService;

    @Autowired
    public HelloController(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @GetMapping("/hello")
    public String sayHello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        model.addAttribute("person", personDetailService.findById(personDetails.getId()));
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}
