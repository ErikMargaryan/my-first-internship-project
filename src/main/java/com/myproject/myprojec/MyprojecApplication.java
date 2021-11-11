package com.myproject.myprojec;

//@RequiredArgsConstructor creates constructor only with @NonNull annotated fields

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyprojecApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojecApplication.class, args);
    }

}
