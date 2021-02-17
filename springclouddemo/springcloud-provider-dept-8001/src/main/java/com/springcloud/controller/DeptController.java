package com.springcloud.controller;

import com.demo.pojo.Dept;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        System.out.println(dept.getDname());
        dept.setDname("tt");
        return deptService.addDept(dept);
    }
//    public boolean addDept(@RequestParam("dname") String dname) {
//        return  deptService.addDept(new Dept(dname));
//    }
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return deptService.queryById(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }
    //注册进来的微服务，获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery() {
        //获得微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services:" + services);
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getServiceId() + "\t");
        }
        return this.discoveryClient;
    }
}
