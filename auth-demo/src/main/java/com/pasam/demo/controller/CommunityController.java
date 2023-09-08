package com.pasam.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @GetMapping
    public ResponseEntity list(){
        List<String> communities=new ArrayList<>();
        communities.add("Oyster");
        communities.add("Risinia");
        return ResponseEntity.ok(communities);
    }
}
