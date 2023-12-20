package com.example.login.orders.model;

import com.example.login.product.model.ProductDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrdersDto {
    private Integer id;

    private ProductDto productDto;
}
