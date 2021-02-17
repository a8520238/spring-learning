package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    //配置负载均衡实现RestTemplate
    //IRule
    //AvailabilityFilteringRule：会先过滤掉，跳闸、访问故障的服务
    //RoundRobin 轮训
    //RandomRule 随机
    //RetryRule 会先按照轮训获取服务，如果获取服务失败，会在指定时间重试
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
