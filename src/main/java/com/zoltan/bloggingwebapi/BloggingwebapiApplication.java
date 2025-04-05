package com.zoltan.bloggingwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class BloggingwebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloggingwebapiApplication.class, args);
    }

}
