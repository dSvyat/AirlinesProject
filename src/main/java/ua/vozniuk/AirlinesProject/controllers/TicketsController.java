package ua.vozniuk.AirlinesProject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.vozniuk.AirlinesProject.models.Ticket;
import ua.vozniuk.AirlinesProject.security.PersonDetails;
import ua.vozniuk.AirlinesProject.services.TicketsService;

import java.util.Arrays;
import java.util.List;

@Controller
public class TicketsController {
    private final TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping("/tickets/buy")
    public String bookTicket(@ModelAttribute("ticket") Ticket ticket, Model model){
        List<String> cities = Arrays.asList("Krakow", "Warsaw", "Gdansk", "Wroclaw");
        model.addAttribute("cities", cities);
        return "tickets/buy";
    }

    @PostMapping("/tickets")
    public String buy(@ModelAttribute("ticket") @Valid Ticket ticket, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "tickets/buy";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();

        ticketsService.saveTicket(ticket, personDetails.getPerson().getId());
        return "redirect:/tickets";

    }

    @GetMapping("/tickets")
    public String show(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails)authentication.getPrincipal();
        model.addAttribute("tickets", ticketsService.getTicketsByPersonId(personDetails.getId()));
        return "/tickets/index";
    }
}
