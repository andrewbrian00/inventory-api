package com.cajap.app;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class CajapApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CajapApiApplication.class, args);
    }

}
