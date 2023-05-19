package ua.vozniuk.AirlinesProject.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.vozniuk.AirlinesProject.models.Person;
import ua.vozniuk.AirlinesProject.security.PersonDetails;
import ua.vozniuk.AirlinesProject.services.PersonDetailService;

@Controller
public class PeopleController {
    private final PersonDetailService personDetailService;

    public PeopleController(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @GetMapping("/person-info")
    public String show(Model model){
        model.addAttribute("person", personDetailService.findById(getCurrentPerson().getId()));
        return "/people/show";
    }

    @GetMapping("/edit")
    public String edit(Model model){
        model.addAttribute("person", personDetailService.findById(getCurrentPerson().getId()));
        return "/people/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/people/edit";
        }

        personDetailService.edit(getCurrentPerson().getId(), person);
        return "redirect:/hello";
    }

    private static PersonDetails getCurrentPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (PersonDetails)authentication.getPrincipal();
    }

}
