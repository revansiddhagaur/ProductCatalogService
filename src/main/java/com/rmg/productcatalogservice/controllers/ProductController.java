package com.rmg.productcatalogservice.controllers;

import com.rmg.productcatalogservice.dtos.ProductDto;
import com.rmg.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return new ArrayList<ProductDto>();
    }
    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") Long productId){
        ProductDto product = new ProductDto();
        product.setId(productId);
        return product;
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productDto;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        return productDto;
    }
}
