package me.soknight.studying.hosting.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class AdminPagesController {

    @SneakyThrows
    @GetMapping("/admin")
    public void redirectToProducts(HttpServletResponse response) {
        response.sendRedirect("/admin/products");
    }

}
