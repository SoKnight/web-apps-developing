package me.soknight.studying.institute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
