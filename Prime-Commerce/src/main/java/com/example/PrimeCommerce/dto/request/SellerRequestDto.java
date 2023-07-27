package com.example.PrimeCommerce.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SellerRequestDto {
    String name;

    String panNo;

    String emailId;
}
