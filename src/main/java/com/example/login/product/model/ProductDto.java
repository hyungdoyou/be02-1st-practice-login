package com.example.login.product.model;

import com.example.login.orders.model.ProductOrdersDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private Integer price;

//    private List<ProductOrdersDto> productOrdersDtoList = new ArrayList<>();
//    private List<OrdersDto> orderDtoList = new ArrayList<>();
}
