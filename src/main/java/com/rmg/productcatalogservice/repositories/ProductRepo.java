package com.rmg.productcatalogservice.repositories;

import com.rmg.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    public Optional<Product> findById(Long id);
}
