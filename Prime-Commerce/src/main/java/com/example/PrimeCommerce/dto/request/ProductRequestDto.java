package com.example.PrimeCommerce.dto.request;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequestDto {

    String sellerEmail;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory productCategory;

}

