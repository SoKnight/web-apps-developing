package me.soknight.studying.hosting.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name = "products")
public final class Product {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "processor")
    private String processor;

    @Column(name = "protection")
    private String protection;

    @Column(name = "vcore")
    private String vcore;

    @Column(name = "ram")
    private String ram;

    @Column(name = "storage")
    private String storage;

    @Column(name = "network")
    private String network;

    @Column(name = "price")
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
