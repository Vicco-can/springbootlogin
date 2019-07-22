package com.company.corporation.springboot.springbootlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.company.corporation.springboot.springbootlogin.dao")
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class SpringbootloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootloginApplication.class, args);
    }

}
