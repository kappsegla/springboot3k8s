package org.fungover.springboot3k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GreeterProperties.class)
public class Springboot3k8sApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3k8sApplication.class, args);
    }

}
