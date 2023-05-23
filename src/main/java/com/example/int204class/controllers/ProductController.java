package com.example.int204class.controllers;

import com.example.int204class.dtos.PageDTO;
import com.example.int204class.dtos.SimpleProductDTO;
import com.example.int204class.entitys.Product;
import com.example.int204class.services.ProductService;
import com.example.int204class.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prod")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ListMapper listMapper;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size,
            @RequestParam(defaultValue = "productCode") String sortBy){
        return productService.getAllProd(page, size, sortBy);
    }

    @GetMapping("/pagedto")
    public PageDTO<SimpleProductDTO> getAllPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size,
            @RequestParam(defaultValue = "productCode") String sortBy){
        Page<Product> productPage = productService.getAllProd(page, size, sortBy);
        return listMapper.toPageDTO(productPage, SimpleProductDTO.class, modelMapper);
    }

    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<Product>> getProdByPrice(@PathVariable Double start, @PathVariable Double end)
    {
        if(start >= 10.00 && end <= 250.00){
            List<Product> prod = productService.getProdByPriceBetween(start, end);
            return ResponseEntity.ok(prod);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
