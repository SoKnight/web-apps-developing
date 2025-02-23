package me.soknight.studying.institute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class AdminPagesController {

    @GetMapping("/admin")
    public String redirectToNews() {
        return "redirect:/admin/news";
    }

}
