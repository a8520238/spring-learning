package com.springcloud.controller;

import com.demo.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //消费者，不应该有服务层
    //RestTemplate 供我们直接调用即可
    //(url, 实体：Map, Class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;

    //通过Ribbon实现的时候，这里应该是一个变量，通过服务名来访问
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add/", dept, Boolean.class);
    }
//    public boolean add(@RequestParam("dname") String dname) {
//        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add/", dname, Boolean.class);
//    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list/", List.class);
    }
}
