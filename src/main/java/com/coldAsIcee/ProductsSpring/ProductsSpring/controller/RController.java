package com.coldAsIcee.ProductsSpring.ProductsSpring.controller;

import com.coldAsIcee.ProductsSpring.ProductsSpring.model.Product;
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
public class RController {

    @Autowired
    ProductService productService;

    // allProducts
    // http://localhost:8080/products - GET
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // create
    // http://localhost:8080/products - POST
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product createProduct() throws IOException {
        Product product = new Product();
        inputProduct(product);
        return productService.createProduct(product);
    }

    // read
    // http://localhost:8080/products/id - GET
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    // update
    // http://localhost:8080/products/update/id - POST
    @RequestMapping(value = "/products/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Product updateProduct(Product product) throws IOException {
        inputProduct(product);
        return productService.updateProduct(product);
    }

    // delete
    // http://localhost:8080/products/id - POST
    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    // JPA Criteria
    // http://localhost:8080/products?search=name:jeans, category:cloth
    @RequestMapping(method = RequestMethod.PUT, value = "/products")
    @ResponseBody
    public List<Product> findAll(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
        return productService.searchProduct(params);
    }

    // Method for create/update product
    public void inputProduct(Product product) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input product name: ");
        product.setName(reader.readLine());
        System.out.println("Input product description: ");
        product.setDescription(reader.readLine());
        System.out.println("Input product category(Food, Clothes): ");
        boolean rightCategory = false;
        while (!rightCategory) {
            String category = reader.readLine();
            if (category.equals("Food") || category.equals("Clothes")) {
                product.setCategory(category);
                rightCategory = true;
            } else
                System.out.println("Wrong product category, input again(Food, Clothes): ");
        }
    }

}
