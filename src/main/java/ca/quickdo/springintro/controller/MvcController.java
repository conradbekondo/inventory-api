package ca.quickdo.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/")
public class MvcController {
    @GetMapping
    public String index(Model model, @RequestParam(required = false, defaultValue = "Default name") String name) {
        model.addAttribute("firstName", name);
        return "index";
    }

}
