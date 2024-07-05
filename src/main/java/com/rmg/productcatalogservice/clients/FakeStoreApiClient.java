package com.rmg.productcatalogservice.clients;

import com.rmg.productcatalogservice.dtos.FakeStoreProductDto;
import com.rmg.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProductById(Long id){

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{productId}",null,FakeStoreProductDto.class,id);

        if(fakeStoreProductDtoResponseEntity.getBody() != null && fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }
    public FakeStoreProductDto[] getAllProducts(){
        return requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products",null,FakeStoreProductDto[].class).getBody();
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDtoReq) {
        return requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{productId}",fakeStoreProductDtoReq,FakeStoreProductDto.class,id).getBody();
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate =restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
