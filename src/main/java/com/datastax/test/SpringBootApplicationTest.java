package com.datastax.test;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(ConnectionConfigProperties.class)
public class SpringBootApplicationTest {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            new Thread(new Runnable() {
                public void run() {
                    ConnectionConfigProperties config = ctx.getBean(ConnectionConfigProperties.class);
                    TestConnection.runTest(config);
                }

            }).run();

        };
    }

}
