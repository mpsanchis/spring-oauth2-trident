package com.mpsanchis.springoauth2resourceserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceServerController {

    @GetMapping("/secret")
    public List<String> products() {
        return List.of("this","is","a","secret");
    }
}
