package com.example.login.member.service;

import com.example.login.member.model.MemberDto;
import com.example.login.member.model.MemberLoginReq;
import com.example.login.member.model.MemberLoginRes;
import com.example.login.orders.model.MemberOrdersDto;
import com.example.login.product.model.ProductReadRes;
import com.example.login.member.model.entity.Member;
import com.example.login.orders.model.entity.Orders;
import com.example.login.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // CREATE
    public void create(MemberDto memberDto) {

        memberRepository.save(Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .build());
    }

    // LIST
    public List<MemberDto> list() {
        List<Member> result = memberRepository.findAll();

        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member member : result) {
            List<MemberOrdersDto> memberOrdersDtos = new ArrayList<>();
            List<Orders> ordersList = member.getOrderList();

            for(Orders orders : ordersList) {
                MemberOrdersDto memberordersDto = MemberOrdersDto.builder()
                        .id(orders.getId())
                        .productDto(ProductReadRes.builder()
                                .id(orders.getProduct().getId())
                                .name(orders.getProduct().getName())
                                .price(orders.getProduct().getPrice())
                                .build())
                        .build();

                memberOrdersDtos.add(memberordersDto);
            }

            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .memberOrdersDtoList(memberOrdersDtos)
                    .build();

            memberDtos.add(memberDto);
        }
        return memberDtos;
    }

    public MemberLoginRes login(MemberLoginReq memberLoginReq) {
        Optional<Member> emailResult = memberRepository.findByEmail(memberLoginReq.getEmail());

        if(emailResult.isPresent() && memberLoginReq.getPassword().equals(emailResult.get().getPassword())) {
            Member member = emailResult.get();
            return MemberLoginRes.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .build();
        } else {
            return null;
        }
    }
    public MemberLoginRes read(String email, String password) {
        Optional<Member> emailResult = memberRepository.findByEmail(email);
        Optional<Member> pwResult = memberRepository.findByPassword(password);


        if(emailResult.isPresent() && pwResult.isPresent()) {
            Member member = emailResult.get();

//            List<MemberOrdersDto> memberOrdersDtos = new ArrayList<>();

//            for(Orders orders : member.getOrderList()) {
//                memberOrdersDtos.add(MemberOrdersDto.builder()
//                                .id(orders.getId())
//                                .productDto(ProductDto.builder()
//                                        .id(orders.getProduct().getId())
//                                        .name(orders.getProduct().getName())
//                                        .price(orders.getProduct().getPrice())
//                                        .build())
//                        .build()
//                );
//            }

            return MemberLoginRes.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .build();
//            return MemberDto.builder()
//                    .id(member.getId())
//                    .email(member.getEmail())
//                    .password(member.getPassword())
//                    .memberOrdersDtoList(memberOrdersDtos)
//                    .build();
        } else {
            return null;
        }
    }
//    public List<MemberDto> list() {
//        List<Member> result = memberRepository.findAll();
//
//        List<MemberDto> memberDtos = new ArrayList<>();
//        for(Member member : result) {
//            List<OrdersDto> ordersDtos = new ArrayList<>();
//            List<Orders> ordersList = member.getOrderList();
//
//            for(Orders orders : ordersList) {
//                OrdersDto ordersDto = OrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .build();
//
//                ordersDtos.add(ordersDto);
//            }
//
//            MemberDto memberDto = MemberDto.builder()
//                    .idx(member.getIdx())
//                    .email(member.getEmail())
//                    .password(member.getPassword())
//                    .orderDtoList(ordersDtos)
//                    .build();
//
//            memberDtos.add(memberDto);
//        }
//        return memberDtos;
//    }

    // READ
//    public MemberDto read(String email) {
//        Optional<Member> result = memberRepository.findByEmail(email);
//
//        if(result.isPresent()) {
//            Member member = result.get();
//
//            List<OrdersDto> ordersDtos = new ArrayList<>();
//
//            for(Orders orders : member.getOrderList()) {
//                ordersDtos.add(OrdersDto.builder()
//                        .idx(orders.getIdx())
//                        .build()
//                );
//            }
//
//            return MemberDto.builder()
//                    .idx(member.getIdx())
//                    .email(member.getEmail())
//                    .password(member.getPassword())
//                    .orderDtoList(ordersDtos)
//                    .build();
//        } else {
//            return null;
//        }
//    }
//    public MemberDto read(Integer idx) {
//        Optional<Member> result = memberRepository.findById(idx);
//
//        if(result.isPresent()) {
//            Member member = result.get();
//
//            List<OrdersDto> ordersDtos = new ArrayList<>();
//
//            for(Orders orders : member.getOrderList()) {
//                ordersDtos.add(OrdersDto.builder()
//                                .idx(orders.getIdx())
//                                .build()
//                );
//            }
//
//            return MemberDto.builder()
//                    .idx(member.getIdx())
//                    .email(member.getEmail())
//                    .password(member.getPassword())
//                    .orderDtoList(ordersDtos)
//                    .build();
//        } else {
//            return null;
//        }
//    }




    // UPDATE
    public void update(MemberDto memberDto) {
        memberRepository.save(Member.builder()
                        .id(memberDto.getId())
                        .email(memberDto.getEmail())
                        .password(memberDto.getPassword())
                        .build()
        );
    }

    // DELETE
    public void delete(Integer id) {
        memberRepository.delete(Member.builder().id(id).build());
    }
}
