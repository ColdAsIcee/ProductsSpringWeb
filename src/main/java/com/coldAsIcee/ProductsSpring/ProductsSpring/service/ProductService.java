package com.coldAsIcee.ProductsSpring.ProductsSpring.service;

import com.coldAsIcee.ProductsSpring.ProductsSpring.model.Product;

import java.util.List;

public interface ProductService {

//    CRUD = create read update delete

    List<Product> getAllProducts(); // getAll

    Product createProduct(Product product); // create

    Product getById(Long id); // read

    Product updateProduct(Product product); // update

    void deleteProductById(long id); // delete

}
