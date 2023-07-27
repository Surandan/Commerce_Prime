package com.example.PrimeCommerce.service.impl;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.dto.request.ProductRequestDto;
import com.example.PrimeCommerce.dto.response.ProductResponseDto;
import com.example.PrimeCommerce.exception.SellerNotFoundException;
import com.example.PrimeCommerce.model.Product;
import com.example.PrimeCommerce.model.Seller;
import com.example.PrimeCommerce.repository.ProductRepository;
import com.example.PrimeCommerce.repository.SellerRepository;
import com.example.PrimeCommerce.service.ProductService;
import com.example.PrimeCommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = productRepository.findByEmailId(productRequestDto.getSellerEmail());
        if(seller == null) throw new SellerNotFoundException("Seller doesn't exists!");

        Product product = ProductTransformer.productReqDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProductList().add(product);

        Seller savedSeller = sellerRepository.save(seller);

        int size = savedSeller.getProductList().size();

        return ProductTransformer.productToProductResDto(seller.getProductList().get(size-1));

    }

    @Override
    public List<ProductResponseDto> getProdByCategoryAndUnderPrice(ProductCategory productCategory, int price) {

        List<Product> productList = productRepository.findByGetProdByCategoryAndUnderPrice(productCategory,price);

        List<ProductResponseDto> dtoList = new ArrayList<>();
        for( Product p : productList) {
            dtoList.add(ProductTransformer.productToProductResDto(p));
        }
        return dtoList;
    }
}
