package com.example.login.product.service;

import com.example.login.product.model.ProductReadRes;
import com.example.login.product.model.entity.Product;
import com.example.login.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public void create(ProductReadRes productDto) {

        productRepository.save(Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build());
    }
    // LIST
    public List<ProductReadRes> list() {
        List<Product> result = productRepository.findAll();

        List<ProductReadRes> productReadResDtos = new ArrayList<>();
        for(Product product : result) {
            ProductReadRes productReadRes = ProductReadRes.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();

            productReadResDtos.add(productReadRes);
        }
        return productReadResDtos;
    }

    // READ
    public ProductReadRes read(Integer id) {
        Optional<Product> result = productRepository.findById(id);

        if(result.isPresent()) {
            Product product = result.get();

            return  ProductReadRes.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        } else {
            return null;
        }
    }

    // UPDATE
    public void update(ProductReadRes productDto) {
        productRepository.save(Product.builder()
                        .id(productDto.getId())
                        .name(productDto.getName())
                        .price(productDto.getPrice())
                        .build()
        );
    }

    // DELETE
    public void delete(Integer id) {
        productRepository.delete(Product.builder().id(id).build());
    }
}
