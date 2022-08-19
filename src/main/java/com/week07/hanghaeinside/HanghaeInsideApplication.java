package com.week07.hanghaeinside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeInsideApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeInsideApplication.class, args);
    }

}
