package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.CardRequestDto;
import com.example.PrimeCommerce.dto.response.CardResponseDto;
import com.example.PrimeCommerce.exception.CustomerNotFoundException;
import com.example.PrimeCommerce.model.Card;
import com.example.PrimeCommerce.model.Customer;
import com.example.PrimeCommerce.repository.CardRepository;
import com.example.PrimeCommerce.repository.CustomerRepository;
import com.example.PrimeCommerce.service.CardService;
import com.example.PrimeCommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Customer customer = customerRepository.findByMobileNo(cardRequestDto.getCustomerMobileNo());
        if (customer == null) throw new CustomerNotFoundException("Customer doesn't exists !");

        Card card = CardTransformer.CardReqDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        Card savedCard = cardRepository.save(card);

        CardResponseDto cardResponseDto = CardTransformer.CardToCardResponseDto(savedCard);
        cardResponseDto.setCardNo(generateMaskedNo(savedCard.getCardNo()));

        return cardResponseDto;

    }

    public String generateMaskedNo(String cardNo) {

        StringBuilder maskedNo = new StringBuilder(cardNo);
        maskedNo.replace(0, 12, new String(new char[12]).replace('\0','X'));

        return maskedNo.toString();
    }
}
