package me.soknight.studying.hosting.controller;

import lombok.AllArgsConstructor;
import me.soknight.studying.hosting.model.ErrorModel;
import me.soknight.studying.hosting.model.Product;
import me.soknight.studying.hosting.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public final class PagesController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public String renderIndexPage(Model model) {
        injectFragment(model, "news", null);
        return "page";
    }

    @GetMapping("/{page}")
    public String renderPage(@PathVariable String page, Model model) {
        switch (page) {
            case "news" -> injectFragment(model, page, null);
            case "catalog" -> injectFragment(model, page, "EasyHost - Каталог услуг");
            case "about-us" -> injectFragment(model, page, "EasyHost - О нас");
            default -> {
                injectFragment(model, "error", "EasyHost - Ошибка");
                ErrorModel.ERROR_NOT_FOUND.injectInto(model);
            }
        }

        if ("catalog".equals(page))
            supplyCatalogItems(model);

        return "page";
    }

    private void supplyCatalogItems(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
    }

    private void injectFragment(Model model, String page, String pageTitle) {
        model.addAttribute("content", "fragments/%s".formatted(page));
        model.addAttribute("page", page);
        model.addAttribute("pageTitle", pageTitle);
    }

}
