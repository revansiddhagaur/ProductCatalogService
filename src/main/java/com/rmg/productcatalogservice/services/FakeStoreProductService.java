package com.rmg.productcatalogservice.services;

import com.rmg.productcatalogservice.clients.FakeStoreApiClient;
import com.rmg.productcatalogservice.dtos.FakeStoreProductDto;
import com.rmg.productcatalogservice.models.Category;
import com.rmg.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long id){
        return from(fakeStoreApiClient.getProductById(id));
    }


    @Override
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product product = from(fakeStoreProductDto);
            products.add(product);
        }

        return products;
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

    private FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());

        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }

        return fakeStoreProductDto;

    }
    @Override
    public Product createProduct(Product product){
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto request = from(product);

        return from(fakeStoreApiClient.replaceProduct(id,request));
    }
}
