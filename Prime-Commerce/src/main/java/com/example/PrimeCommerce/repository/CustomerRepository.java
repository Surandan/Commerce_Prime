package com.example.PrimeCommerce.repository;

import com.example.PrimeCommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByMobileNo(String customerMobileNo);
}
