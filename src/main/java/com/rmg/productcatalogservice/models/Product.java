package com.rmg.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Product extends BaseModel{
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
