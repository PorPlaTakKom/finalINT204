package com.example.int204class.services;

import com.example.int204class.entitys.Product;
import com.example.int204class.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> getAllProd(Integer page, Integer size, String sortBy){
        Sort s = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, s);
        return productRepository.findAll(pageable);
    }

    public List<Product> getProdByPriceBetween(Double start, Double end){
        return productRepository.findByPriceBetweenOrderByPriceAsc(start, end);
    }
}
