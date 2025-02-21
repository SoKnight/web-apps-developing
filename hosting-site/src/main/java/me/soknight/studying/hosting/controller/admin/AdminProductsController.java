package me.soknight.studying.hosting.controller.admin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.soknight.studying.hosting.model.ErrorModel;
import me.soknight.studying.hosting.model.Product;
import me.soknight.studying.hosting.model.ProductDto;
import me.soknight.studying.hosting.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

import static me.soknight.studying.hosting.util.FragmentInjector.injectAdminFragment;
import static me.soknight.studying.hosting.util.FragmentInjector.injectErrorFragment;

@Controller
@AllArgsConstructor
public final class AdminProductsController {

    private final ProductRepository productRepository;

    @GetMapping("/admin/products")
    public String renderProductsPage(Model model) {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Order.asc("id")));
        model.addAttribute("products", products);
        injectAdminFragment(model, "products", "EasyHost - Услуги");
        return "admin_page";
    }

    @GetMapping("/admin/products/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formTitle", "Добавление новой услуги");
        model.addAttribute("formButton", "Добавить");
        injectAdminFragment(model, "crud/product", "EasyHost - Добавление услуги");
        return "admin_page";
    }

    @PostMapping("/admin/products/create")
    public String createProduct(@Valid ProductDto dto) {
        productRepository.save(new Product(dto));
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{productId}/edit")
    public String editProductPage(@PathVariable long productId, Model model) {
        productRepository.findById(productId).ifPresentOrElse(
                product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("formTitle", "Редактирование услуги");
                    model.addAttribute("formButton", "Сохранить");
                    injectAdminFragment(model, "crud/product", "EasyHost - Добавление услуги");
                },
                () -> injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND)
        );

        return "admin_page";
    }

    @PostMapping("/admin/products/{productId}/edit")
    public String editProduct(@PathVariable long productId, @Valid ProductDto dto, Model model) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND);
            return "admin_page";
        }

        product.get().update(dto);
        productRepository.save(product.get());
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{productId}/delete")
    public String deleteProduct(@PathVariable long productId) {
        productRepository.deleteById(productId);
        return "redirect:/admin/products";
    }

}
