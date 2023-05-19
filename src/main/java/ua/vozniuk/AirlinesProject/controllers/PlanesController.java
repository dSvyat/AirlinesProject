package ua.vozniuk.AirlinesProject.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.vozniuk.AirlinesProject.models.Plane;
import ua.vozniuk.AirlinesProject.services.PlanesService;


@Controller
@RequestMapping("/planes")
public class PlanesController {
    private final PlanesService planesService;

    public PlanesController(PlanesService planesService) {
        this.planesService = planesService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("planes", planesService.findAll());
        return "planes/index";
    }

    @GetMapping("/new")
    public String newPlane(@ModelAttribute("plane") Plane plane){
        return "planes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("plane") @Valid Plane plane, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "planes/new";
        }

        planesService.save(plane);
        return "redirect:/planes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("plane", planesService.findById(id));
        return "planes/edit";
    }

    @PostMapping ("/{id}/edit")
    public String update(@ModelAttribute("plane") @Valid Plane plane, @PathVariable("id") int id,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "planes/edit";
        }

        planesService.edit(id, plane);
        return "redirect:/planes";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        planesService.delete(id);
        return "redirect:/planes";
    }
}
