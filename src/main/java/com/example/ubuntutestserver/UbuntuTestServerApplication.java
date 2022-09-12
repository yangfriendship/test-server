package com.example.ubuntutestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class UbuntuTestServerApplication extends SpringBootServletInitializer {
public class UbuntuTestServerApplication {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(UbuntuTestServerApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(UbuntuTestServerApplication.class, args);
    }

}
