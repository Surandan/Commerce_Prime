package com.example.PrimeCommerce.model;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;

    int price;

    int availableQuantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();

}
