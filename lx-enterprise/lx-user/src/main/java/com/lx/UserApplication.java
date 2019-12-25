package com.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;


@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class
})
//@EnableEurekaClient
//@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
