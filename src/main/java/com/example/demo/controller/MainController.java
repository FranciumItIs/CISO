package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
    	model.addAttribute("pageTitle", "Home Page");
        return "home";
    }
    
    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model) {
        return "loginSuccess";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("user") && password.equals("password")) {
            return "home";
        } 
    	else {
            model.addAttribute("error", "Invalid username or password");
            return "home";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/logoutSuccess";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }

    @GetMapping("/NavigationCarsUrl")
    public String NavigationCars() {
        return "Cars";
    }

    @GetMapping("/NavigationCompanyUrl")
    public String NavigationCompany() {
        return "Companies";
    }
}