package com.springcloud.service;

import com.demo.pojo.Dept;

import java.util.List;


public interface DeptService {
    public boolean addDept(Dept dept);
    public Dept queryById(Long id);
    public List<Dept> queryAll();
}
