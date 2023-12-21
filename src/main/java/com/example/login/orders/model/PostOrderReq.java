package com.example.login.orders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostOrderReq {

    @JsonProperty("Member_id")
    private Integer memberId;

    @JsonProperty("Product_id")
    private Integer productId;
}
