package me.soknight.studying.hosting.controller;

import lombok.AllArgsConstructor;
import me.soknight.studying.hosting.model.Product;
import me.soknight.studying.hosting.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static me.soknight.studying.hosting.util.FragmentInjector.injectFragment;

@Controller
@AllArgsConstructor
public final class PagesController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public String redirectToNews(Model model) {
        injectFragment(model, "news", null);
        return "page";
    }

    @GetMapping("/news")
    public String renderNewsPage(Model model) {
        injectFragment(model, "news", null);
        return "page";
    }

    @GetMapping("/catalog")
    public String renderCatalogPage(Model model) {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("products", products);
        injectFragment(model, "catalog", "EasyHost - Каталог услуг");
        return "page";
    }

    @GetMapping("/about-us")
    public String renderAboutUsPage(Model model) {
        injectFragment(model, "about-us", "EasyHost - О нас");
        return "page";
    }

}
