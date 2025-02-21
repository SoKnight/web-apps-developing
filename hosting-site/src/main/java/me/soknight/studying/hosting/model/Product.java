package me.soknight.studying.hosting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity @Table(name = "products")
public final class Product {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "processor", nullable = false)
    private String processor;

    @Column(name = "protection", nullable = false)
    private String protection;

    @Column(name = "vcore", nullable = false)
    private String vcore;

    @Column(name = "ram", nullable = false)
    private String ram;

    @Column(name = "storage", nullable = false)
    private String storage;

    @Column(name = "network", nullable = false)
    private String network;

    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders;

    public Product(ProductDto dto) {
        this(
                dto.name(), dto.processor(), dto.protection(),
                dto.vcore(), dto.ram(), dto.storage(), dto.network(),
                dto.price()
        );
    }

    public Product(
            String name, String processor, String protection,
            String vcore, String ram, String storage, String network,
            int price
    ) {
        this.name = name;
        this.processor = processor;
        this.protection = protection;
        this.vcore = vcore;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.price = price;
    }

    public void update(ProductDto dto) {
        this.name = dto.name();
        this.processor = dto.processor();
        this.protection = dto.protection();
        this.vcore = dto.vcore();
        this.ram = dto.ram();
        this.storage = dto.storage();
        this.network = dto.network();
        this.price = dto.price();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processor='" + processor + '\'' +
                ", protection='" + protection + '\'' +
                ", vcore='" + vcore + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", network='" + network + '\'' +
                ", price=" + price +
                '}';
    }

}
