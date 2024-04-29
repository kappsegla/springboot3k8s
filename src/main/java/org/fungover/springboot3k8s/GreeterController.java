package org.fungover.springboot3k8s;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterController {

    @Value("${greeting.message}")
    String prefix;

    @GetMapping("/greet/{user}")
    public String greet(@PathVariable("user") String user) {
        //String prefix = System.getenv().getOrDefault("GREETING_PREFIX", "Hi");

        return String.format("%s %s", prefix, user);
    }
}
