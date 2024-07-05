package com.rmg.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel{
    private String name;
    private String description;

    private List<Product> productList;
}
