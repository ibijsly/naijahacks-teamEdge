package com.securedEdgePay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {


    @RequestMapping({"/dashboard", "/home"})
    public String landingPage(){
        return "dashboard";
    }

}
