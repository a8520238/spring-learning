package com.lgz.nacos.order.lgzorderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class OrderController {

    @Value("${member.name}")
    private String memberName;

    @Value("${member.age}")
    private String memberAge;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("getOrder")
    public String getOrder() {

//        String s = "A订单" + restTemplate.getForObject("http://study-service/course/get", String.class);
        return memberName + memberAge;
    }
}
