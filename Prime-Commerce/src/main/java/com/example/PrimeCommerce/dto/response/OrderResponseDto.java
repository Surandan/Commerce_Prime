package com.example.PrimeCommerce.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String customerName;

    String orderId;

    String card;

    List<ItemResponseDto> items;

    int totalPrice;

    Date orderDate;

}
