package com.example.login.member.model.entity;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Orders> orderList = new ArrayList<>();
}
