package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.dto.request.ItemRequestDto;
import com.example.PrimeCommerce.dto.response.CartResponseDto;
import com.example.PrimeCommerce.model.Cart;
import com.example.PrimeCommerce.model.Customer;
import com.example.PrimeCommerce.model.Item;
import com.example.PrimeCommerce.model.Product;
import com.example.PrimeCommerce.repository.CartRepository;
import com.example.PrimeCommerce.repository.CustomerRepository;
import com.example.PrimeCommerce.repository.ItemRepository;
import com.example.PrimeCommerce.repository.ProductRepository;
import com.example.PrimeCommerce.service.CartService;
import com.example.PrimeCommerce.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity() * product.getPrice());

        item.setProduct(product);
        item.setCart(cart);

        Item savedItem = itemRepository.save(item);

        cart.getItemList().add(item);
        product.getItemList().add(item);

        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        return CartTransformer.CartToCartReponseDto(savedCart);
    }
}
