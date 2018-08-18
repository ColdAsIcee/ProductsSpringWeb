package com.coldAsIcee.ProductsSpring.ProductsSpring.controller;


import com.coldAsIcee.ProductsSpring.ProductsSpring.model.Product;
import com.coldAsIcee.ProductsSpring.ProductsSpring.service.ProductDAO;
import com.coldAsIcee.ProductsSpring.ProductsSpring.service.ProductService;
import com.coldAsIcee.ProductsSpring.ProductsSpring.service.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    ProductService productService;

    // Отображение всего списка - allProducts
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Создаем - create
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product createProduct(Product product) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Product product1 = new Product();
        product1.setName(reader.readLine());
        return productService.createProduct(product1);
    }

    // Заходим в один объект - read
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    // Обновялем - update
    @RequestMapping(value = "/products/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    // Удаляем - delete
    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    // find
    @Autowired
    private ProductDAO productDAO;
    //    http://localhost:8080/products?search=name:jeans, category:cloth
    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    @ResponseBody
    public List<Product> findAll(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
        return productDAO.searchProduct(params);
    }

}
