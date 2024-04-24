package org.fungover.springboot3k8s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.UUID;

@RestController
public class EndpointsController {

    private final static UUID uuid = UUID.randomUUID();

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public Information hello() throws UnknownHostException {
        // Get IP Address
        String ipAddress = InetAddress.getLocalHost().getHostAddress();

        // Get Hostname
        String hostname = InetAddress.getLocalHost().getHostName();

        return new Information("Version 1",ipAddress, hostname, ZonedDateTime.now(), Locale.getDefault(), uuid);
    }

    @PostMapping("/die")
    public void die(){
        System.exit(-1);
    }

    record Information(String address, String ipAddress, String hostname, ZonedDateTime dateTime, Locale locale, UUID uuid) {
    }
}
