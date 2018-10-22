package com.securedEdgePay.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @RequestMapping(value = "/login")
    public ModelAndView getLoginForm(
            @RequestParam(required = false) String authfailed, String logout, String denied, String forgot, String change, String activation) {

        String message = "";

        if (authfailed != null) {
            message = "Invalid username or password, try again !";
        } else if (logout != null) {
            message = "Logged Out successfully, login again to continue !";
        } else if (denied != null) {
            message = "Access denied for this user !";
        } else if(forgot != null) {
            message = "Please check your email for a reset token";
        } else if (change != null){
            message = "Password Reset Successful";
        }else if (activation != null)
            message = "Activation Successful";
        return new ModelAndView("login", "message", message);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
