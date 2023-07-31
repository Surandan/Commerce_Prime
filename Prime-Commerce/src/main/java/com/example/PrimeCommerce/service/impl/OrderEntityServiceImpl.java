package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.Enum.ProductStatus;
import com.example.PrimeCommerce.dto.request.OrderRequestDto;
import com.example.PrimeCommerce.dto.response.OrderResponseDto;
import com.example.PrimeCommerce.exception.CustomerNotFoundException;
import com.example.PrimeCommerce.exception.InsufficientQuantityException;
import com.example.PrimeCommerce.exception.InvalidCardException;
import com.example.PrimeCommerce.exception.ProductNotFoundException;
import com.example.PrimeCommerce.model.*;
import com.example.PrimeCommerce.repository.CardRepository;
import com.example.PrimeCommerce.repository.CustomerRepository;
import com.example.PrimeCommerce.repository.OrderEntityRepository;
import com.example.PrimeCommerce.repository.ProductRepository;
import com.example.PrimeCommerce.service.OrderEntityService;
import com.example.PrimeCommerce.transformer.ItemTransformer;
import com.example.PrimeCommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardServiceImpl cardService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer == null) throw new CustomerNotFoundException("Customer not Found!");

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException("Invalid product id!");
        Product product = optionalProduct.get();

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date today = new Date();
        if(card==null || today.after(card.getValidTill()) || card.getCvv() != orderRequestDto.getCvv()) {
            throw  new InvalidCardException("Invalid card!");
        }

        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Insufficient Quantity available");
        }

        int updatedQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(updatedQuantity);

        if(updatedQuantity==0){
            product.setProductStatus(ProductStatus.UNAVAILABLE);
        }

        // Lest prepare order

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setOrderTotal(product.getPrice()* orderRequestDto.getRequiredQuantity());
        orderEntity.setCardUsed(cardService.generateMaskedNo(card.getCardNo()));

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrderEntity(orderEntity);

        orderEntity.getItemList().add(item);
        orderEntity.setCustomer(customer);

        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);

        sendEmail(orderEntity);

        return OrderTransformer.OrderToOrderResponseDto(savedOrderEntity);
    }

    public void sendEmail(OrderEntity orderEntity) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("primecommercemail@gmail.com");
        mailMessage.setTo(orderEntity.getCustomer().getEmailId());
        mailMessage.setSubject("Order successfully placed");

        String text = "Congrats! Your order has been placed. Details of order as follows: " + "\n"
                + "Order Id = "+ orderEntity.getOrderId() + "\n"
                + "Order total = " + orderEntity.getOrderTotal() + " INR\n"
                + "Order Date = " + orderEntity.getOrderDate();

        mailMessage.setText(text);

        javaMailSender.send(mailMessage);
    }

    @Override
    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generateMaskedNo(card.getCardNo()));


        Date today = new Date();
        if(card==null || today.after(card.getValidTill()) || card.getCvv() != card.getCvv()) {
            throw  new InvalidCardException("Invalid card!");
        }


        int cartTotal = 0;
        for(Item item : cart.getItemList()) {
            Product product = item.getProduct();
            if(item.getRequiredQuantity() > product.getAvailableQuantity()) {
                throw new InsufficientQuantityException("Sorry! " + product.getProductName()+" Out of Stock! Please reduce te quantity of "+product.getProductName());
            }
            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            if(newQuantity == 0) product.setProductStatus(ProductStatus.UNAVAILABLE);
            product.setAvailableQuantity(newQuantity);

            cartTotal += (item.getRequiredQuantity() * product.getPrice());
            item.setOrderEntity(order);
        }

        order.setOrderDate(new Date());
        order.setOrderTotal(cartTotal);
        order.setItemList(cart.getItemList());
        order.setCustomer(cart.getCustomer());

        return order;
    }
}
