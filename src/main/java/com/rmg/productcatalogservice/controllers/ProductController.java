package com.rmg.productcatalogservice.controllers;

import com.rmg.productcatalogservice.dtos.CategoryDto;
import com.rmg.productcatalogservice.dtos.ProductDto;
import com.rmg.productcatalogservice.models.Product;
import com.rmg.productcatalogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//https://www.scaler.com/academy/mentee-dashboard/class/241887/session?joinSession=1
    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return new ArrayList<ProductDto>();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        try {
            if(productId <= 0)
                throw new IllegalArgumentException("Product id must be valid");

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Called By","Internal API");
        Product product = productService.getProductById(productId);
        if(product == null) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto,headers, HttpStatus.OK);
        }
        catch (IllegalArgumentException iae){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    private ProductDto from(Product product) {
        CategoryDto categoryDto = null;
        if (product.getCategory() != null) {
            categoryDto = CategoryDto.builder()
                    .name(product.getCategory().getName())
                    .id(product.getCategory().getId())
                    .description(product.getCategory().getDescription())
                    .build();
        }

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .category(categoryDto)
                .build();
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }
}
