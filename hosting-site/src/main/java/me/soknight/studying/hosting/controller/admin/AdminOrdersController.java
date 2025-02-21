package me.soknight.studying.hosting.controller.admin;

import lombok.AllArgsConstructor;
import me.soknight.studying.hosting.model.Order;
import me.soknight.studying.hosting.repository.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static me.soknight.studying.hosting.util.FragmentInjector.injectAdminFragment;

@Controller
@AllArgsConstructor
public final class AdminOrdersController {

    private final OrderRepository orderRepository;

    @GetMapping("/admin/orders")
    public String renderOrdersPage(Model model) {
        List<Order> orders = orderRepository.findAll(Sort.by(Sort.Order.desc("createdAt")));
        model.addAttribute("orders", orders);
        injectAdminFragment(model, "orders", "EasyHost - Заказы");
        return "admin_page";
    }

    @GetMapping("/admin/orders/{orderId}/delete")
    public String deleteOrder(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
        return "redirect:/admin/orders";
    }

}
