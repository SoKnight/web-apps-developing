package me.soknight.studying.hosting.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.soknight.studying.hosting.model.ErrorModel;
import me.soknight.studying.hosting.model.Order;
import me.soknight.studying.hosting.model.OrderDto;
import me.soknight.studying.hosting.repository.OrderRepository;
import me.soknight.studying.hosting.repository.ProductRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static me.soknight.studying.hosting.util.FragmentInjector.injectErrorFragment;
import static me.soknight.studying.hosting.util.FragmentInjector.injectFragment;

@Controller
@AllArgsConstructor
public final class OrdersController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @SneakyThrows
    @GetMapping("/order")
    public String renderPage(@RequestParam(value = "id", required = false) Long id, Model model, HttpServletResponse response) {
        if (id == null) {
            response.sendRedirect("/catalog");
            return null;
        }

        productRepository.findById(id).ifPresentOrElse(
                product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("products", productRepository.findAll());
                    injectFragment(model, "order", "EasyHost - Оформление заказа");
                },
                () -> injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND)
        );

        return "page";
    }

    @PostMapping(value = "/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String makeOrder(@Valid OrderDto dto, Model model                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ) {
        productRepository.findById(dto.id().longValue()).ifPresentOrElse(
                product -> {
                    Order order = new Order(dto, product);
                    orderRepository.save(order);

                    model.addAttribute("order", order);
                    model.addAttribute("product", product);
                    injectFragment(model, "order_complete", "EasyHost - Заказ оформлен");
                },
                () -> injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND)
        );

        return "page";
    }

}
