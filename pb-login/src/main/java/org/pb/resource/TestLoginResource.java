package org.pb.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLoginResource {

    @GetMapping("/home")
    public String home() {
        return "Public";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String Admin() {
        return "Admin";
    }

}
