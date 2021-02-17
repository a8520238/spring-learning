package com.config;

import com.TestA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com")
public class JavaBeanConfig {
//    @Bean(name = "TestA")
    @Bean
    public TestA testA() {
        return new TestA();
    }
}
