package com.example.login.product.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReadRes {

    private Integer id;
    private String name;
    private Integer price;

//    private List<ProductOrdersDto> productOrdersDtoList = new ArrayList<>();
//    private List<OrdersDto> orderDtoList = new ArrayList<>();
}
