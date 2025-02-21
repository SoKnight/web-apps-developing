package me.soknight.studying.hosting.repository;

import me.soknight.studying.hosting.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
