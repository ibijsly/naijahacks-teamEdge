package com.securedEdgePay.controller;

import com.securedEdgePay.dto.AuthRequestDTO;
import com.securedEdgePay.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthApiController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/api/v1/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> login(@RequestBody AuthRequestDTO requestDTO){
        return authService.login(requestDTO.getUsername(), requestDTO.getPassword());
    }

    @PostMapping(value = "/api/v1/token/refresh", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> refreshToken(@RequestBody AuthRequestDTO requestDTO){
        return authService.login(requestDTO.getUsername(), requestDTO.getPassword());
    }

}
