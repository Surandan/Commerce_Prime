package com.example.PrimeCommerce.dto.request;

import com.example.PrimeCommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardRequestDto {

    String customerMobileNo;

    String cardNo;

    int cvv;

    Date validTill;

    CardType cardType;
}
