package com.rmg.productcatalogservice.services;

import com.rmg.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);
}
