package me.soknight.studying.hosting.controller;

import lombok.AllArgsConstructor;
import me.soknight.studying.hosting.model.ErrorModel;
import me.soknight.studying.hosting.model.Product;
import me.soknight.studying.hosting.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public final class OrdersController {

    private static final ErrorModel ERROR_PRODUCT_NOT_FOUND = new ErrorModel(
            "Ошибка!",
            "Указанный продукт не найден \uD83D\uDE15"
    );

    private final ProductRepository productRepository;

    @GetMapping("/order")
    public String renderPage(@RequestParam("id") long id, Model model) {
        productRepository.findById(id).ifPresentOrElse(
                product -> {
                    model.addAttribute("content", "fragments/order");
                    model.addAttribute("page", "order");
                    model.addAttribute("pageTitle", "EasyHost - Оформление заказа");
                    model.addAttribute("product", product);
                },
                () -> {
                    model.addAttribute("content", "fragments/error");
                    model.addAttribute("page", "error");
                    model.addAttribute("pageTitle", "EasyHost - Ошибка");
                    ERROR_PRODUCT_NOT_FOUND.injectInto(model);
                }
        );

        return "page";
    }

}
