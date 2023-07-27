package com.example.PrimeCommerce.repository;

import com.example.PrimeCommerce.Enum.ProductCategory;
import com.example.PrimeCommerce.model.Product;
import com.example.PrimeCommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select s from Seller as s where s.emailId = :sellerEmail")
    public Seller findByEmailId(String sellerEmail);

    @Query("select p from Product as p where p.productCategory = :productCategory and p.price <= :price")
    public List<Product> findByGetProdByCategoryAndUnderPrice(ProductCategory productCategory,int price);
}
