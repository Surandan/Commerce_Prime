package com.example.PrimeCommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderRequestDto {

    String customerEmail;

    int productId;

    String cardUsed;

    int cvv;

    int requiredQuantity;
}
