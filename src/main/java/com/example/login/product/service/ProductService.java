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
//            List<ProductOrdersDto> productOrdersDtos = new ArrayList<>();
//            List<Orders> ordersList = product.getOrderList();

//            for(Orders orders : ordersList) {
//                ProductOrdersDto productOrdersDto = ProductOrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .memberDto(MemberDto.builder()
//                                .idx(orders.getMember().getIdx())
//                                .email(orders.getMember().getEmail())
//                                .password(orders.getMember().getPassword())
//                                .build())
//                        .build();
//
//                productOrdersDtos.add(productOrdersDto);
//            }

            ProductReadRes productReadRes = ProductReadRes.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
//                    .productOrdersDtoList(productOrdersDtos)
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

//            List<ProductOrdersDto> productOrdersDtos = new ArrayList<>();

//            for(Orders orders : product.getOrderList()) {
//                productOrdersDtos.add(ProductOrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .memberDto(MemberDto.builder()
//                                .idx(orders.getMember().getIdx())
//                                .email(orders.getMember().getEmail())
//                                .password(orders.getMember().getPassword())
//                                .build())
//                        .build()
//                );
//            }

            return  ProductReadRes.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
//                    .productOrdersDtoList(productOrdersDtos)
                    .build();
        } else {
            return null;
        }
    }

//    // LIST
//    public List<ProductDto> list() {
//        List<Product> result = productRepository.findAll();
//
//        List<ProductDto> productDtos = new ArrayList<>();
//        for(Product product : result) {
//            List<OrdersDto> ordersDtos = new ArrayList<>();
//            List<Orders> ordersList = product.getOrderList();
//
//            for(Orders orders : ordersList) {
//                OrdersDto ordersDto = OrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .build();
//
//                ordersDtos.add(ordersDto);
//            }
//
//            ProductDto productDto = ProductDto.builder()
//                    .idx(product.getIdx())
//                    .name(product.getName())
//                    .price(product.getPrice())
//                    .orderDtoList(ordersDtos)
//                    .build();
//
//            productDtos.add(productDto);
//        }
//        return productDtos;
//    }
//
//    // READ
//    public ProductDto read(Integer idx) {
//        Optional<Product> result = productRepository.findById(idx);
//
//        if(result.isPresent()) {
//            Product product = result.get();
//
//            List<OrdersDto> ordersDtos = new ArrayList<>();
//
//            for(Orders orders : product.getOrderList()) {
//                ordersDtos.add(OrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .build()
//                );
//            }
//
//            return  ProductDto.builder()
//                    .idx(product.getIdx())
//                    .name(product.getName())
//                    .price(product.getPrice())
//                    .orderDtoList(ordersDtos)
//                    .build();
//        } else {
//            return null;
//        }
//    }

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
