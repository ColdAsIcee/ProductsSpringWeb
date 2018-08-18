package com.coldAsIcee.ProductsSpring.ProductsSpring.repositories;

import com.coldAsIcee.ProductsSpring.ProductsSpring.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}