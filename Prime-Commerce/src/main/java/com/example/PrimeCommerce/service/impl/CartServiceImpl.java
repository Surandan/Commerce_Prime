package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.CheckOutRequestDto;
import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.dto.response.CartResponseDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.exception.CustomerNotFoundException;
import com.example.PrimeCommerce.exception.EmptyCartException;
import com.example.PrimeCommerce.exception.InvalidCardException;
import com.example.PrimeCommerce.model.*;
import com.example.PrimeCommerce.repository.*;
import com.example.PrimeCommerce.service.CartService;
import com.example.PrimeCommerce.transformer.CartTransformer;
import com.example.PrimeCommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderEntityServiceImpl orderEntityService;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity() * product.getPrice());

        item.setProduct(product);
        item.setCart(cart);

        Item savedItem = itemRepository.save(item);

        cart.getItemList().add(savedItem);
        product.getItemList().add(savedItem);

        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        return CartTransformer.CartToCartReponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutRequestDto checkOutRequestDto) {

        Customer customer = customerRepository.findByEmailId(checkOutRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Date currentDate = new Date();
        Card card = cardRepository.findByCardNo(checkOutRequestDto.getCardNo());
        if(card==null || card.getCvv()!= checkOutRequestDto.getCvv() || currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Card is not valid");
        }

        Cart cart = customer.getCart();
        if(cart.getItemList().isEmpty()) throw new EmptyCartException("Sorry! The cart is empty");

        OrderEntity orderEntity = orderEntityService.placeOrder(cart,card);

        orderEntityService.sendEmail(orderEntity);

        resetCart(cart);

        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }

    public void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for(Item item : cart.getItemList()) {
            item.setCart(null);
        }
        cart.setItemList(new ArrayList<>());
    }
}

