package com.securedEdgePay.controller;

import com.securedEdgePay.dto.ResponseDTO;
import com.securedEdgePay.service.VerificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerificationController {
    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET)
    public ResponseDTO verifyCustomer(@PathVariable(name = "type", required = true) String type, @PathVariable(name = "id", required = true) String id){

        return verificationService.verify(type, id);
    }
}
