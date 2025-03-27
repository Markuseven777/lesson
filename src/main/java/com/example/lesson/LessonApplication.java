package com.example.lesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;

import java.io.Console;
import java.util.logging.Logger;

@SpringBootApplication
public class LessonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessonApplication.class, args);
    }

    @Controller
    static
    class HelloController {

    }

}
