package me.soknight.studying.hosting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name = "orders")
public final class Order {

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "post_index", nullable = false)
    private String postIndex;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "city_address", nullable = false)
    private String cityAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "comment")
    private String comment;

    public Order(OrderDto dto, Product product) {
        this(
                dto.name(), dto.email(), dto.phoneNumber(),
                dto.country(), dto.postIndex(), dto.city(), dto.cityAddress(),
                product, dto.quantity(), dto.comment()
        );
    }

    public Order(
            String name, String email, String phoneNumber,
            String country, String postIndex, String city, String cityAddress,
            Product product, int quantity, String comment
    ) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.postIndex = postIndex;
        this.city = city;
        this.cityAddress = cityAddress;
        this.product = product;
        this.quantity = quantity;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", postIndex='" + postIndex + '\'' +
                ", city='" + city + '\'' +
                ", cityAddress='" + cityAddress + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                '}';
    }

}
