package com.example.int204class;

import com.example.int204class.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Int204ClassApplication {
    public static void main(String[] args) {
        SpringApplication.run(Int204ClassApplication.class, args);
    }

}
