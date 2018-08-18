package com.coldAsIcee.ProductsSpring.ProductsSpring.service;

import com.coldAsIcee.ProductsSpring.ProductsSpring.model.Product;

import java.util.List;

public interface ProductService {


    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product getById(Long id);

    Product updateProduct(Product product);

    void deleteProductById(long id);

    // JPA Criteria
    List<Product> searchProduct(List<SearchCriteria> params);

    void save(Product product);

}
