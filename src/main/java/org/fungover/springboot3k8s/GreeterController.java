package org.fungover.springboot3k8s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterController {

    Logger logger = LoggerFactory.getLogger(GreeterController.class);

    private final GreeterProperties greeterProperties;

    public GreeterController(GreeterProperties greeterProperties) {
        this.greeterProperties = greeterProperties;
    }

    @GetMapping("/greet/{user}")
    public String greet(@PathVariable("user") String user) {
        //String prefix = System.getenv().getOrDefault("GREETING_PREFIX", "Hi");
        return String.format("%s %s", greeterProperties.getMessage(), user);
    }

    /**
     * This method reacts to the refresh event and prints properties value.
     */
    @EventListener(RefreshScopeRefreshedEvent.class)
    public void reactOnRefresh(RefreshScopeRefreshedEvent event) {
        logger.info("Refresh has happened, prefix = {}", greeterProperties.getMessage());
    }
}
