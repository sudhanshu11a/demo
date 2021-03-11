/**
 *
 */
package org.sudhanshu.demo.user.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sudhanshu.demo.user.dto.AuthenticationRequest;
import org.sudhanshu.demo.user.dto.User;
import org.sudhanshu.demo.user.feign.OrganizationServiceProxy;

/**
 * @author Sudhanshu Sharma
 *
 */
@RestController()
public class UserController {

    //@Autowired
    //private AuthenticationManager manager;

    @Autowired
    private OrganizationServiceProxy organizationService;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    //@PostMapping("/authenticate")
    public User getUsers(@RequestBody Map<String, Object> credentials) {
        System.out.println("Authenticate starts");
        String result = organizationService.getHello();
        User user = new User(1, "Sudhanshu", "password", result, "lastName", "token");
        return user;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String result = organizationService.getHello();
        User user1 = new User(1, "Sudhanshu", "password", "Sudhanshu", result, "token");
        User user2 = new User(1, "Himanshu", "password", "Himanshu", result, "token");
        users.add(user1);
        users.add(user2);
        return users;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
    return ResponseEntity.ok("token");
    }

}
