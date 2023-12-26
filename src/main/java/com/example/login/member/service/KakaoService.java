package com.example.login.member.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KakaoService {

    public String getKakaoToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "ca49904e4f88fe41677a3ce0ccf9dbd1");
        params.add("redirect_uri", "http://localhost:8080/member/kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                Object.class
        );

        String result = "" + response;
        String accessToken = "" + result.split(",")[1].split("=")[1];

        return accessToken;
    }
    public String getUserInfo(String accessToken) {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers2.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity request2 = new HttpEntity<>(headers2);
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<Object> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request2,
                Object.class
        );

        String result2 = "" + response2.getBody();
        String userName = result2.split("nickname=")[1].split("}")[0];

        return userName;
    }
}
