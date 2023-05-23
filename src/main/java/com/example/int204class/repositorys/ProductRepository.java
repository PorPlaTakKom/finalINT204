package com.example.int204class.repositorys;

import com.example.int204class.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByPriceBetweenOrderByPriceAsc(Double start, Double end);
}
