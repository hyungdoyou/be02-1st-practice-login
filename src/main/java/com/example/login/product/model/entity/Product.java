package com.example.login.product.model.entity;

import com.example.login.orders.model.entity.Orders;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<Orders> orderList = new ArrayList<>();
}
