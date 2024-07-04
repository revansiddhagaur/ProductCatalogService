package com.rmg.productcatalogservice.services;

import com.rmg.productcatalogservice.dtos.FakeStoreProductDto;
import com.rmg.productcatalogservice.models.Category;
import com.rmg.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate
                .getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class,id);
        if(fakeStoreProductDtoResponseEntity.getBody() != null && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = Product.builder()
                .name(fakeStoreProductDto != null ? fakeStoreProductDto.getTitle() : null)
                .description(fakeStoreProductDto != null ? fakeStoreProductDto.getDescription() : null)
                .price(fakeStoreProductDto != null ? fakeStoreProductDto.getPrice() : null)
                .imageUrl(fakeStoreProductDto != null ? fakeStoreProductDto.getImage() : null)
                .category(Category.builder()
                        .name(fakeStoreProductDto != null ? fakeStoreProductDto.getCategory() : null)
                        .build())
                .build();
        product.setId(fakeStoreProductDto != null ? fakeStoreProductDto.getId() : null);
        return product;
    }

    @Override
    public List<Product> getAllProducts(){
        return null;
    }

    @Override
    public Product createProduct(Product product){
        return null;
    }
}
