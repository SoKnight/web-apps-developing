package me.soknight.studying.hosting.repository;

import me.soknight.studying.hosting.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
