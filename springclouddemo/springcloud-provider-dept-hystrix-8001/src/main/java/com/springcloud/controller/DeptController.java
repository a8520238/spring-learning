package com.springcloud.controller;

import com.demo.pojo.Dept;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    //服务熔断
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("id=>" + id + "不存在该用户");
        }
        return dept;
    }

    //备选方法
    public Dept hystrixGet(@PathVariable("id") Long id) {

//        Dept dept = new Dept();
//        dept.setDeptno(id);
//        dept.setDname("id=>" + id + "不存在该用户");
//        dept.setDb_source("no this database in MySQL");
//        return dept;
        return new Dept()
                .setDeptno(id)
                .setDname("id=>" + id + "不存在该用户")
                .setDb_source("no this database in MySQL");
    }

}
