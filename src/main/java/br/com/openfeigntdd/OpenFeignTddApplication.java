package br.com.openfeigntdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignTddApplication.class, args);
    }

}
