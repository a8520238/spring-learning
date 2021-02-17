package com.nacosstudy.nacosstudyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("course")
public class StudyController {
    @GetMapping("get")
    public String getCourse() {
        return "Java虚拟机";
    }
}
