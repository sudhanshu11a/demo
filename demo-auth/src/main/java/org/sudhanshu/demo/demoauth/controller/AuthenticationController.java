package org.sudhanshu.demo.demoauth.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.sudhanshu.demo.demoauth.dto.AuthenticationRequest;
import org.sudhanshu.demo.demoauth.dto.TokenResponse;
import org.sudhanshu.demo.demoauth.jwt.JwtUtil;
import org.sudhanshu.demo.demoauth.services.MyUserDetailsService;

/**
 * @author Sudhanshu Sharma
 */
@RestController
@RequestMapping(path = "/token")
@CrossOrigin()
@Api(value = "auth", description = "Endpoint for user management")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final TokenResponse jwt = new TokenResponse(jwtTokenUtil.generateToken(userDetails));
        return ResponseEntity.ok(jwt);
    }

    @PostMapping(value = "/validate")
    public ResponseEntity<?> validateAuthenticationToken(@RequestBody TokenResponse token) throws Exception {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token.getToken()));
        return ResponseEntity.ok(jwtTokenUtil.validateToken(token.getToken(), userDetails));
    }

}
