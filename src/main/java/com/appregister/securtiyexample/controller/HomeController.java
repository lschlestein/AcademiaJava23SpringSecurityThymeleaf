package com.appregister.securtiyexample.controller;

import com.appregister.securtiyexample.model.UserDtls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.appregister.securtiyexample.service.UserService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("session")
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute HttpServletRequest httpServletRequest) {
        return "register";
    }

    @PostMapping("/logout")
    public String logout() {
        System.out.println("Logout");
        return "redirect:/login";
    }

    @PostMapping("/createUser")
    public String createuser(@ModelAttribute UserDtls user, HttpSession session) {

        // System.out.println(user);

        boolean f = userService.checkEmail(user.getEmail());

        if (f) {
            session.setAttribute("msg", "Email Id alreday exists");
        } else {
            UserDtls userDtls = userService.createUser(user);
            if (userDtls != null) {
                session.setAttribute("msg", "Register Sucessfully");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

}
