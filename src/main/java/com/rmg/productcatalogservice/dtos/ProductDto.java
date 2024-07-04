package com.rmg.productcatalogservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    private CategoryDto category;
}
