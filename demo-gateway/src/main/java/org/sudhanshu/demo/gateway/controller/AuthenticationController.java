package org.sudhanshu.demo.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.sudhanshu.demo.gateway.dto.AuthenticationRequest;
import org.sudhanshu.demo.gateway.dto.TokenResponse;
import org.sudhanshu.demo.gateway.jwt.JwtUtil;
import org.sudhanshu.demo.gateway.services.MyUserDetailsService;

/**
 * @author Sudhanshu Sharma
 */
@RestController
@RequestMapping(path = "/token")
@CrossOrigin
public class AuthenticationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

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
        Authentication authentication = null;
        try {
            authentication = manager.authenticate(
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
