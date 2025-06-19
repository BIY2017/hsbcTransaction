package com.hsbc.transaction.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hsbc.transaction"})
@EnableAspectJAutoProxy
public class TransactionStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionStarterApplication.class, args);
    }

}
