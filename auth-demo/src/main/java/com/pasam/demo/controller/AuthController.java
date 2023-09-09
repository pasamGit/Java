package com.pasam.demo.controller;

import com.pasam.demo.model.AuthRequest;
import com.pasam.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthRequest request) throws Exception {
        if(request!=null){
            try{
                manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword(),new ArrayList<>()));
            }catch (BadCredentialsException ex){
                System.out.println("bad credentials");
                throw new Exception("Bad Credentials",ex);
            }
            final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUserName());
            return ResponseEntity.accepted().body(jwtTokenUtil.generateToken(userDetails));

        }
        return ResponseEntity.badRequest().build();
    }


}
