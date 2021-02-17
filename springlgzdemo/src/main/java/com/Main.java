package com;

import com.config.JavaBeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaBeanConfig.class);
//        TestA testA = (TestA) context.getBean(TestA.class);
        TestA testA = (TestA) context.getBean("testA");
        testA.test();
        context.close();
    }
}
