package com.example.PrimeCommerce.dto.request;

import com.example.PrimeCommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerAddReqDto {


    String name;

    String mobileNo;

    String emailId;

    Gender gender;
}
