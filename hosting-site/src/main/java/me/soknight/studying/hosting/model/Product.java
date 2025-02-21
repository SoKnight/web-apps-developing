package me.soknight.studying.hosting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
