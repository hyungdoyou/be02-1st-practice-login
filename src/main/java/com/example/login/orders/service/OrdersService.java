package com.example.login.orders.service;

import com.example.login.member.model.MemberDto;
import com.example.login.orders.model.OrdersDto;
import com.example.login.product.model.ProductDto;
import com.example.login.member.model.entity.Member;
import com.example.login.orders.model.entity.Orders;
import com.example.login.product.model.entity.Product;
import com.example.login.orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository orderRepository) {
        this.ordersRepository = orderRepository;
    }

    // CREATE
    public void create(Integer memberid, Integer productid, OrdersDto orderDto) {

        ordersRepository.save(Orders.builder()
                .member(Member.builder().id(memberid).build())
                .product(Product.builder().id(productid).build())
                .build());
    }

    // LIST
    public List<OrdersDto> list(){
        List<Orders> result = ordersRepository.findAll();

        List<OrdersDto> ordersDtos = new ArrayList<>();

        for(Orders orders : result) {
            Member member = orders.getMember();
            Product product = orders.getProduct();

            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .build();

            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();

            OrdersDto ordersDto = OrdersDto.builder()
                    .id(orders.getId())
                    .memberDto(memberDto)
                    .productDto(productDto)
                    .build();

            ordersDtos.add(ordersDto);
        }
        return ordersDtos;
    }

    // READ
    public OrdersDto read(Integer idx) {
        Optional<Orders> result = ordersRepository.findById(idx);
        if(result.isPresent()) {
            Orders orders = result.get();

            return OrdersDto.builder()
                    .id(orders.getId())
                    .memberDto(MemberDto.builder()
                            .id(orders.getMember().getId())
                            .email(orders.getMember().getEmail())
                            .password(orders.getMember().getPassword())
                            .build())
                    .productDto(ProductDto.builder()
                            .id(orders.getProduct().getId())
                            .name(orders.getProduct().getName())
                            .price(orders.getProduct().getPrice())
                            .build())

                    .build();
        } else {
            return null;
        }
    }



    // UPDATE
    public void update(OrdersDto ordersDto) {
        Optional<Orders> result = ordersRepository.findById(ordersDto.getId());

        if(result.isPresent()) {
            Orders orders = result.get();
            ordersRepository.save(orders);
        }
    }

    // DELETE
    public void delete(Integer id) {
        ordersRepository.delete(Orders.builder().id(id).build());
    }
}
